package me.sonam.dev.fastandroiddemo;

import me.sonam.dev.corelibs.entity.BaseData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SonamLee on 2017/3/8.
 */
public interface ApiService {
    @GET(Urls.LOGIN)
    Observable<BaseData> getLogin(@Query("username") String username, @Query("password") String password);
}
