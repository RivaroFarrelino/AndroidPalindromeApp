package com.example.suitmediamobiledevelopertestquestion1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediamobiledevelopertestquestion1.models.User
import com.example.suitmediamobiledevelopertestquestion1.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreen : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var userAdapter: UserAdapter
    private val userList = mutableListOf<User>()
    private var currentPage = 1
    private var totalPages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        btnBack = findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList) { user ->
            val intent = Intent(this, SecondScreen::class.java)
            intent.putExtra("SELECTED_USER", "${user.first_name} ${user.last_name}")
            startActivity(intent)
        }
        recyclerView.adapter = userAdapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        swipeRefreshLayout.setOnRefreshListener {
            loadUsers(1)
        }

        loadUsers(currentPage)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && currentPage < totalPages) {
                    currentPage++
                    loadUsers(currentPage)
                }
            }
        })

        btnBack.setOnClickListener{
            startActivity(Intent(this, SecondScreen::class.java))
        }
    }

    private fun loadUsers(page: Int) {
        swipeRefreshLayout.isRefreshing = page == 1

        RetrofitClient.apiService.getUsers(page, 10).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let {
                        totalPages = it.total_pages
                        if (page == 1) {
                            userList.clear()
                        }
                        userList.addAll(it.data)
                        userAdapter.notifyDataSetChanged()

                        if (userList.isEmpty()) {
                            findViewById<TextView>(R.id.emptyStateTextView).visibility = View.VISIBLE
                        } else {
                            findViewById<TextView>(R.id.emptyStateTextView).visibility = View.GONE
                        }
                    }
                } else {
                    Toast.makeText(this@ThirdScreen, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(this@ThirdScreen, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}