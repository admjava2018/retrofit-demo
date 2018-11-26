package es.indra.demoapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.indra.demoapp1.models.Categoria;
import es.indra.demoapp1.rest.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listado;
    ArrayList<String> listadoArray = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listadoArray);
        listado = findViewById(R.id.list);

        listado.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getCategorias();
    }

    private void getCategorias() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.149:8090/categoria/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestService rest = retrofit.create(RestService.class);
        Call<List<Categoria>> call = rest.getAllCategories();

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                for(Categoria cat : response.body()) {
                    listadoArray.add(cat.getNombre());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
