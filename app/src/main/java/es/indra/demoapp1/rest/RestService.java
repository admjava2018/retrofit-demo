package es.indra.demoapp1.rest;

import java.util.List;

import es.indra.demoapp1.models.Categoria;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {

    String API_ROUTE = "/categoria";

    @GET(API_ROUTE)
    Call<List<Categoria>> getAllCategories();

}