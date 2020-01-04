package com.silverpants.movieapp.recycler

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.silverpants.movieapp.pojo.discover.Result
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.silverpants.movieapp.*

class MovieAdapter : PagedListAdapter<Result, MovieAdapter.MovieViewHolder>(

        object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return true
            }
        }) {

     private var movieClickListener: MovieClickListener? = null

    fun setMovieClickListener(movieClickListener: MovieClickListener) {
        this.movieClickListener = movieClickListener
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(v)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val result = getItem(position)

        Glide.with(holder.mBackgroundImage.context).load(getBackgroundImageUrl(result?.backdropPath,1)).into(holder.mBackgroundImage)
        holder.mTitleField.text = result?.title
        holder.mDescriptionField.text = result?.overview
        holder.mRatingBar.rating = result?.voteAverage?.toFloat()?.div(2) ?: 0.0f

        holder.genres.removeAllViews()
        result?.genreIds?.iterator()?.forEach {
            val chip = Chip(holder.genres.context)
            chip.text = genres[it]
            chip.isClickable = true
            chip.isActivated = true
            holder.genres.addView(chip)
        }
        


        holder.mBackgroundImage.setOnClickListener {
            movieClickListener?.onClick(getItem(position)?.id ?: FROZEN2)
        }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mBackgroundImage: ImageView = itemView.findViewById(R.id.movie_background)
        val mTitleField : TextView = itemView.findViewById(R.id.movie_title)
        val mRatingBar :RatingBar = itemView.findViewById(R.id.movie_rating)
        val mDescriptionField: TextView = itemView.findViewById(R.id.movie_description)
        val genres: ChipGroup = itemView.findViewById(R.id.movie_genre_chip)

    }
}
