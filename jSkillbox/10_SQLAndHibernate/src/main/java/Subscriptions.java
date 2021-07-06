import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    private int subscriptionDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(int subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
