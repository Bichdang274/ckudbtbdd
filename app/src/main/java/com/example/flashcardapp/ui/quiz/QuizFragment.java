package com.example.flashcardapp.ui.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.flashcardapp.R;
import com.example.flashcardapp.databinding.FragmentQuizBinding;
import com.example.flashcardapp.viewmodel.QuizViewModel;
import java.util.Arrays;
import java.util.List;

public class QuizFragment extends Fragment {
    private FragmentQuizBinding b;
    private QuizViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle s) {
        b = FragmentQuizBinding.inflate(i, c, false); return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(QuizViewModel.class);

        String setId = getArguments() != null ? getArguments().getString("setId") : null;
        String folderId = getArguments() != null ? getArguments().getString("folderId") : null;
        if (setId != null && !setId.isEmpty()) vm.loadSet(setId);
        else if (folderId != null && !folderId.isEmpty()) vm.loadFolder(folderId);

        b.btnBack.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
        b.btnRestart.setOnClickListener(v -> vm.restart());
        b.btnBackToHome.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        List<Button> optionButtons = Arrays.asList(b.btnOption0, b.btnOption1, b.btnOption2, b.btnOption3);
        for (int idx = 0; idx < optionButtons.size(); idx++) {
            final int i2 = idx;
            optionButtons.get(idx).setOnClickListener(v -> vm.selectAnswer(i2));
        }

        observeViewModel();
    }

    private void observeViewModel() {
        List<Button> optionButtons = Arrays.asList(b.btnOption0, b.btnOption1, b.btnOption2, b.btnOption3);

        vm.questions.observe(getViewLifecycleOwner(), questions -> {
            b.tvTotal.setText("/" + questions.size());
            if (!questions.isEmpty()) {
                b.quizContent.setVisibility(View.VISIBLE);
                b.resultScreen.setVisibility(View.GONE);
            }
        });

        vm.currentIndex.observe(getViewLifecycleOwner(), idx -> {
            List<QuizViewModel.QuizQuestion> qs = vm.questions.getValue();
            if (qs == null || idx >= qs.size()) return;
            QuizViewModel.QuizQuestion q = qs.get(idx);
            int total = qs.size();
            b.tvProgress.setText(String.valueOf(idx + 1));
            b.progressBar.setProgress((idx + 1) * 100 / total);

            if ("vi-to-en".equals(q.type)) {
                b.tvEmoji.setText("🇻🇳");
                b.tvQuestion.setText("\"" + q.card.vietnamese + "\"\ntiếng Anh là gì?");
                for (int i = 0; i < optionButtons.size(); i++) {
                    optionButtons.get(i).setText(i < q.options.size() ? q.options.get(i).english : "");
                }
            } else {
                b.tvEmoji.setText(q.card.emoji);
                b.tvQuestion.setText("\"" + q.card.english + "\"\nnghĩa là gì?");
                for (int i = 0; i < optionButtons.size(); i++) {
                    optionButtons.get(i).setText(i < q.options.size() ? q.options.get(i).vietnamese : "");
                }
            }

            for (Button btn : optionButtons) {
                btn.setBackgroundResource(R.drawable.bg_option_default);
                btn.setTextColor(Color.WHITE);
                btn.setAlpha(1f);
                btn.setEnabled(true);
            }
            b.cardQuestion.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_up));
        });

        vm.selectedAnswer.observe(getViewLifecycleOwner(), selected -> {
            if (selected == null) return;
            QuizViewModel.QuizQuestion q = vm.getCurrentQuestion();
            if (q == null) return;
            for (int i = 0; i < optionButtons.size(); i++) {
                Button btn = optionButtons.get(i);
                btn.setEnabled(false);
                if (i == q.correctIndex) { btn.setBackgroundResource(R.drawable.bg_option_correct); btn.setAlpha(1f); }
                else if (i == selected) { btn.setBackgroundResource(R.drawable.bg_option_wrong); btn.setAlpha(1f); }
                else btn.setAlpha(0.4f);
            }
        });

        vm.score.observe(getViewLifecycleOwner(), s -> b.tvScore.setText("Điểm: " + s));

        vm.timeLeft.observe(getViewLifecycleOwner(), t -> {
            b.tvTimeLeft.setText(String.valueOf(t));
            b.timerBar.setProgress(t * 100 / 15);
            int color;
            if (t > 10) color = Color.parseColor("#4ADE80");
            else if (t > 5) color = Color.parseColor("#FACC15");
            else color = Color.parseColor("#EF4444");
            b.tvTimeLeft.setTextColor(color);
        });

        vm.showStreak.observe(getViewLifecycleOwner(), show -> {
            if (Boolean.TRUE.equals(show)) {
                Integer streak = vm.streak.getValue();
                b.tvStreakOverlay.setText("🔥 Streak x" + (streak != null ? streak : 0) + "!");
                b.tvStreakOverlay.setVisibility(View.VISIBLE);
            } else {
                b.tvStreakOverlay.setVisibility(View.GONE);
            }
        });

        vm.showResult.observe(getViewLifecycleOwner(), show -> { if (Boolean.TRUE.equals(show)) showResult(); });
    }

    private void showResult() {
        b.quizContent.setVisibility(View.GONE);
        b.tvStreakOverlay.setVisibility(View.GONE);
        b.resultScreen.setVisibility(View.VISIBLE);

        int score = vm.score.getValue() != null ? vm.score.getValue() : 0;
        int total = vm.questions.getValue() != null ? vm.questions.getValue().size() : 1;
        int pct = score * 100 / total;
        int best = vm.bestStreak.getValue() != null ? vm.bestStreak.getValue() : 0;

        b.tvResultEmoji.setText(pct >= 80 ? "🏆" : pct >= 60 ? "🎉" : pct >= 40 ? "👍" : "💪");
        b.tvResultScore.setText(score + "/" + total);
        b.tvBestStreak.setText("🔥 Best streak: " + best);
        b.tvResultSubtitle.setText(pct >= 80 ? "Xuất sắc! Bạn học rất tốt!" :
            pct >= 60 ? "Tốt lắm! Tiếp tục cố gắng!" :
            pct >= 40 ? "Khá tốt! Ôn lại nhé!" : "Cần ôn thêm! Cố lên!");
        b.circularProgress.setProgress(pct);
        b.tvPct.setText(pct + "%");
    }

    @Override
    public void onDestroyView() { super.onDestroyView(); b = null; }
}
