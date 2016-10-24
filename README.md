# Retrofit 2.0 with RxJava Smaple

### What is RxJava?

RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.


It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety and concurrent data structures.

- Zero Dependencies

- < 1MB Jar

- Java 6+ & Android 2.3+

- Java 8 lambda support

- Polyglot (Scala, Groovy, Clojure and Kotlin)

- Non-opinionated about source of concurrency (threads, pools, event loops, fibers, actors, etc)

- Async or synchronous execution

- Virtual time and schedulers for parameterized concurrency

### Why you should use RxJava in Android with Retrofit?


RxJava is exceptionally good when data is sent as a stream. The Retrofit Observable pushes all elements on the stream at the same time. This isn't particularly useful in itself compared to Callback. But when there are multiple elements pushed on the stream and different times, and you need to do timing-related stuff, RxJava makes the code a lot more maintainable.

### Step to Add RxJava with Retrofit.

#### Step 1

###### Add depency injection for retrofit and RxJava/RxAndroid

```
dependencies {  
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'io.reactivex:rxjava:1.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'
    compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
    compile 'com.squareup.okhttp:okhttp:2.4.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
}
```

###### Create Retrofit Service.

```
public interface ApiInterface {

    @GET("search/images")
    rx.Observable<GettyConfig> getImage(@Query("file_types") String file_types)
    
    }
```
And Create Service class

```
public class CreateSevice {

    public static final String API_BASE_URL = "https://api.gettyimages.com/v3/";
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static <S> S createService(Class<S> serviceClass, final String ApiKey ) {
      Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create());

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Api-key", ApiKey)
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
```





