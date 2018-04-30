package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
//                book.setName("The Lost Symbol");
//                book.setAuthor("Dan Brown");
//                book.setPages(510);
//                book.setPrice(19.95);
//                book.setPress("Unknow");
//                book.save();
//                book.setPrice(10.99);
//                book.save();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });

        Button queryDate = findViewById(R.id.query_data);
        queryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "book name is " + book.getName());
                    Log.d(TAG, "book author is " + book.getAuthor());
                    Log.d(TAG, "book pages is " + book.getPages());
                    Log.d(TAG, "book price is " + book.getPrice());
                    Log.d(TAG, "book press is " + book.getPress());
                }

                // 查询第一条数据：Boook FirstBook = DataSupport.findFirst(Book.class);
                // 查询最后一条数据：Book lastBook = DataSupport.Last(Book.class);
                // 指定查询哪几列数据：List<Book> books = DataSupport.select("name", "author").find(Book.class);
                // 指定查询的约束条件：List<Book> books = DataSupport.where("pages > ?", "400").find(Book.class);
                // 指定结果的排列方式：List<Book> books = DataSupport.order("price desc").find(Book.class);
                // 指定查询结果的数量：List<Book> books = DataSupport.limit(3).find(Book.class);
                // 指定查询结果的偏移量：List<Book> books = DataSupport.limit(3).offset(1).find(Book.class);
                // 查询表中第11～20条满足页数大于400这个条件的name、author和pages这3列数据：
                /*
                    List<Book> books = DataSupport.select("name", "author", "pages")
                                                  .where("pages > ?", 400)
                                                  .order("pages")
                                                  .limit(10)
                                                  .offset(10)
                                                  .find(Book.class);
                 */
                // 原生SQL语句：Cursor c = DataSupport.findBySQL("select * from Book where pages > ? and price < ?", "400", "20");
            }
        });
    }
}
