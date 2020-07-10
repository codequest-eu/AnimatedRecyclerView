package com.codequest.animatedrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.quanti.quase.loremkotlinum.Lorem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val postsAdapter: PostsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        postsRecycler.adapter = postsAdapter

        postsAdapter.submitList(createPosts())
    }

    private fun createPosts(): List<Post> =
        List(100) { Post(Lorem.tweet(), Lorem.name(), Lorem.email()) }
}
