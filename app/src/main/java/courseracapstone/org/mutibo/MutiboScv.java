package courseracapstone.org.mutibo;

import android.content.Context;
import android.content.Intent;

import courseracapstone.org.mutibo.oauth.SecuredRestBuilder;
import courseracapstone.org.mutibo.unsafe.EasyHttpClient;
import retrofit.RestAdapter;
import retrofit.client.ApacheClient;

/**
 * Created by Ruben on 01/11/2014.
 */
public class MutiboScv {

    public static final String CLIENT_ID = "mobile";

    private static MutiboSvcApi MutiboSvc_;

    public static synchronized MutiboSvcApi getOrShowLogin(Context ctx) {
        if (MutiboSvc_ != null) {
            return MutiboSvc_;
        } else {
            Intent i = new Intent(ctx, LoginActivity.class);
            ctx.startActivity(i);
            return null;
        }
    }

    public static synchronized MutiboSvcApi init(String server, String user, String pass) {

        MutiboSvc_ = new SecuredRestBuilder()
                .setLoginEndpoint(server + MutiboSvcApi.TOKEN_PATH)
                .setUsername(user)
                .setPassword(pass)
                .setClientId(CLIENT_ID)
                .setClient(
                        new ApacheClient(new EasyHttpClient()))
                .setEndpoint(server).setLogLevel(RestAdapter.LogLevel.FULL).build()
                .create(MutiboSvcApi.class);

        return MutiboSvc_;
    }
}
