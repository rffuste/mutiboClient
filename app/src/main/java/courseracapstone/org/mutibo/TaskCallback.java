package courseracapstone.org.mutibo;

/**
 * Created by Ruben on 01/11/2014.
 */
public interface TaskCallback<T> {

    public void success(T result);

    public void error(Exception e);

}
