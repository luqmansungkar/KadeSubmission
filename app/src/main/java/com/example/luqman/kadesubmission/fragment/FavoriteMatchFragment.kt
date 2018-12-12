package com.example.luqman.kadesubmission.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.FavoriteMatchAdapter
import com.example.luqman.kadesubmission.database.database
import com.example.luqman.kadesubmission.model.Favorite
import com.example.luqman.kadesubmission.ui.FavoriteListUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.find

class FavoriteMatchFragment : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listFavorite: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(favorites)
        listFavorite = find(R.id.list_favorite)
        listFavorite.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FavoriteListUI().createView(AnkoContext.create(requireContext()))
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
