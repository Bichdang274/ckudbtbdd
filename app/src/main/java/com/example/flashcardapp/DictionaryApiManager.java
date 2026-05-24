package com.example.flashcardapp;

import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public class DictionaryApiManager {

    public static class DictEntry {
        public String word, phonetic;
        public List<Phonetic> phonetics;
        public List<Meaning> meanings;

        public static class Phonetic {
            public String text;
        }

        public static class Meaning {
            public String partOfSpeech;
            public List<Definition> definitions;

            public static class Definition {
                public String definition, example;
            }
        }

        public String getPhonetic() {
            if (phonetic != null && !phonetic.isEmpty()) return phonetic;
            if (phonetics != null) {
                for (Phonetic p : phonetics) {
                    if (p.text != null && !p.text.isEmpty()) return p.text;
                }
            }
            return "";
        }
    }

    public interface DictApi {
        @GET("api/v2/entries/en/{word}")
        Call<List<DictEntry>> lookup(@Path("word") String word);
    }

    private static DictApi instance;

    public static synchronized DictApi getApi() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/")
                .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder().setLenient().create()))
                .build()
                .create(DictApi.class);
        }
        return instance;
    }
}
