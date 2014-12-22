package courseracapstone.org.mutibo;

/**
 * Created by Ruben on 01/11/2014.
 */
 import android.support.annotation.NonNull;

 import com.google.common.base.Objects;

 import java.util.Collection;
 import java.util.Iterator;


public class SetQuestion  {

    private long id;

    private String title1;
    private String title2;
    private String title3;
    private String title4;
    private int odd;
    private String answer;
    private int rating;


    public SetQuestion()
    {

    }

    public SetQuestion(String title1, String title2, String title3, String title4, int odd, String answer) {
        super();
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
        this.title4 = title4;
        this.odd = odd;
        this.answer = answer;
        this.rating = 5;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle1() {
        return title1;
    }
    public void setTitle1(String title1) {
        this.title1 = title1;
    }
    public String getTitle2() {
        return title2;
    }
    public void setTitle2(String title2) {
        this.title2 = title2;
    }
    public String getTitle3() {
        return title3;
    }
    public void setTitle3(String title3) {
        this.title3 = title3;
    }
    public String getTitle4() {
        return title4;
    }
    public void setTitle4(String title4) {
        this.title4 = title4;
    }
    public int getOdd() {
        return odd;
    }
    public void setOdd(int odd) {
        this.odd = odd;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj instanceof SetQuestion) {
            SetQuestion other = (SetQuestion) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(this.title1, other.title1)
                    && Objects.equal(this.title2, other.title2)
                    && Objects.equal(this.title3, other.title3)
                    && Objects.equal(this.title4, other.title4)
                    && this.odd == other.odd
                    && Objects.equal(this.answer, other.answer)
                    && this.rating == other.rating;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }


    @Override
    public String toString() {
        return "SetQuestion [id=" + id + ", title1=" + title1 + ", title2="
                + title2 + ", title3=" + title3 + ", title4=" + title4
                + ", odd=" + odd + ", answer=" + answer + ", rating=" + rating
                + "]";
    }




}
