package com.codingwithjks.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.rxjava.Adapater.FoodAdapter
import com.codingwithjks.rxjava.Model.Food
import com.codingwithjks.rxjava.Network.Retrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foodAdapter= FoodAdapter(this,ArrayList<Food>())
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=foodAdapter
        }

        //A Disposable is a stream or a link between an Observable and an Observer
        val compositeDisposable= CompositeDisposable() //obj creation of compositeDisposable

        //getObservable() me data save h, wo add karna compositeDisposable me,
        // i.e bg thread me Schedulers.io() / subscribe karwana bg thread me
        compositeDisposable.add(getObservable().subscribeOn(Schedulers.io())

                //bg thread se main thread me show karwana
            .observeOn(AndroidSchedulers.mainThread())

            //subscribe contain 2 lambda fun
            // 1. response
            // 2. error i.e call onFailure() fun
            .subscribe ({ response->getObserver(response as ArrayList<Food>)},
                {t->onFailure(t)}
            ))
    }


    //this fun contain data , we have to give it to getObserver fun via CompositeDisposable
    private fun getObservable():Observable<List<Food>>
    {
        return Retrofit.api.getAllFoodList()
    }

    private fun getObserver(foodList:ArrayList<Food>)
    {
        if(foodList!= null && foodList.size!=0)
        {
            foodAdapter.setData(foodList)
        }
    }

    private fun onFailure(t:Throwable)
    {
        Log.d("main", "onFailure: "+t.message)
    }

}
