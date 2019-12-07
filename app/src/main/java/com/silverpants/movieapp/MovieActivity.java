package com.silverpants.movieapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialAutoCompleteTextView;
import com.silverpants.movieapp.adapter.MovieAdapter;
import com.silverpants.movieapp.viewmodel.MovieViewModel;

public class MovieActivity extends AppCompatActivity {


    private RecyclerView mMovieRecycler;
    private MovieAdapter movieAdapter;
    private TextInputEditText mYearField;
    private MaterialAutoCompleteTextView mSortByField;
    private MaterialButton mApplyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }


    private void init() {
        mMovieRecycler = findViewById(R.id.movie_item_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mMovieRecycler.setLayoutManager(manager);
        movieAdapter = new MovieAdapter();
        mApplyButton = findViewById(R.id.movie_apply_button);
        mYearField = findViewById(R.id.movie_year_field);
        mSortByField = findViewById(R.id.movie_sortby_field);

        String[] sortBy = getResources().getStringArray(R.array.movie_sortby);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.movie_dropdown_menu_popup_item, sortBy);

        mSortByField.setAdapter(adapter);

        MovieViewModel model = ViewModelProviders.of(this).get(MovieViewModel.class);

        mApplyButton.setOnClickListener((v) -> {
                    if (!movieValidateForm()) {
                        return;
                    }
                    movieAdapter.getCurrentList().getDataSource().invalidate();
                    model.dataInvalidate();

                    Toast.makeText(this, "" + mSortByField.getText().toString(), Toast.LENGTH_SHORT).show();
                    int index = 0;
                    for (int i = 0; i < sortBy.length; i++) {
                        if (sortBy[i].equals(mSortByField.getText().toString())) {
                            index = i;
                            break;
                        }
                    }

                    model.getDiscover(Integer.parseInt(mYearField.getText().toString()), Keys.SORT_BY_VALUES[index]).observe(MovieActivity.this, (resultPagedList) -> {
                        movieAdapter.submitList(resultPagedList);
                        mMovieRecycler.setAdapter(movieAdapter);

                    });

                }
        );

        model.getDiscover().observe(this, (resultPagedList) -> {
            Log.d("BLAH","one bro");
            movieAdapter.submitList(resultPagedList);
            mMovieRecycler.setAdapter(movieAdapter);

        });


    }

    boolean movieValidateForm() {
        if (mYearField.getText().toString().equals("")) {
            mYearField.setError("Enter Valid Year");
            return false;
        }
        try {
            Integer.parseInt(mYearField.getText().toString());

        } catch (NumberFormatException e) {
            mYearField.setError("Enter Valid Year");
            return false;
        }
        if (mSortByField.getText().toString().equals("")) {
            Toast.makeText(this, "Select Sorting Option", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
