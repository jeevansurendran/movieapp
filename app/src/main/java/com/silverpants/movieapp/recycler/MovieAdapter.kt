package com.silverpants.movieapp.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.silverpants.movieapp.R
import com.silverpants.movieapp.pojo.discover.Result
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.silverpants.movieapp.FROZEN2

import com.silverpants.movieapp.IMAGE_SIZE_1
import com.silverpants.movieapp.IMAGE_URL

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
        holder.mReleaseDate.text = getItem(position)!!.releaseDate
        holder.mTitle.text = getItem(position)!!.title
        holder.mOverView.text = getItem(position)!!.overview
        holder.mVoteAverage.text = getItem(position)!!.voteAverage!!.toString()

        Glide.with(holder.mImage.context).load(IMAGE_URL + IMAGE_SIZE_1 + getItem(position)!!.posterPath).into(holder.mImage)
        Log.d("blah", "onBindViewHolder: yes insdie blah")

        holder.mImage.setOnClickListener {
            movieClickListener?.onClick(getItem(position)?.id ?: FROZEN2)
        }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mImage: ImageView = itemView.findViewById(R.id.movie_image)
        var mReleaseDate: TextView = itemView.findViewById(R.id.movie_release_date)
        var mTitle: TextView = itemView.findViewById(R.id.movie_title)
        var mOverView: TextView = itemView.findViewById(R.id.movie_overview)
        var mVoteAverage: TextView = itemView.findViewById(R.id.movie_vote_average)

    }
}
