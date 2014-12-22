package courseracapstone.org.mutibo;

import java.util.Collection;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Ruben on 01/11/2014.
 */
public interface MutiboSvcApi {

    public static final String RATING_PARAMETER = "rating";

    public static final String TOKEN_PATH = "/oauth/token";

    // The path where we expect the MutiboSvc to live
    public static final String MUTIBO_SVC_PATH = "/mutibo";
    public static final String MUTIBO_USERS = MUTIBO_SVC_PATH + "/users";
    public static final String MUTIBO_SETS_REFRESH = MUTIBO_SVC_PATH + "/refresh";


    @GET(MUTIBO_SVC_PATH)
    public List<SetQuestion> getSetList();

    @POST(MUTIBO_SVC_PATH)
    public SetQuestion addSet(@Body SetQuestion s);

    @POST(MUTIBO_USERS)
    public MutiboUser getUserDetails(@Body MutiboUser u);

    @POST(MUTIBO_SETS_REFRESH)
    public List<SetQuestion> refreshSetRatings(@Body List<SetQuestion> setUserList);


}
