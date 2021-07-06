import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        // формируем строки запроса студента, курса и списка соответствий имени курса и имени студента
        String studentHql = "From " + Student.class.getSimpleName();
        String courseHql = "From " + Course.class.getSimpleName();
        String purchaceListHql = "From " + PurchaceList.class.getSimpleName();

        // Обходим в цикле в записи соответствий
        List<PurchaceList> purchaceList = session.createQuery(purchaceListHql).getResultList();
        for(PurchaceList plist : purchaceList){
            // Находим студента по имени
            Student student = (Student) session.createQuery(studentHql + " where name like \"" + plist.getStudentName() + "\"").uniqueResult();
            // Находим курс по имени
            Course course = (Course) session.createQuery(courseHql + " where name like \"" + plist.getCourseName() + "\"").uniqueResult();
            // СОздаем объект новой таблицы
            LinkedPurchaseList pl = new LinkedPurchaseList();
            // Задаем значение курса и студента и записываем
            pl.setCourseId(course.getId());
            pl.setStudentId(student.getId());
            session.save(pl);
        }

        // Блок проверки
        String hql = "From " + LinkedPurchaseList.class.getSimpleName();
        List<LinkedPurchaseList> linkedPurchaseList = session.createQuery(hql).getResultList();
        System.out.println("Количество записей в таблице = " + linkedPurchaseList.size());
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Course> query = builder.createQuery(Course.class);
//        Root<Course> root = query.from(Course.class);
//        query.select(root).where(builder.greaterThan(root.<Integer>get("price"), 100000))
//                .orderBy(builder.desc(root.get("price")));
//        System.out.println("Кол-во курсов у студента " + session.get(Student.class, 1).getCourses().size());
//        System.out.println("Кол-во курсов у преподавателя c id = 1" + session.get(Teacher.class, 1).getCourses().size());

        sessionFactory.close();
    }
}
