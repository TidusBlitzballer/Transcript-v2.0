import java.util.*;

public class Transcript
{
  Student student;
  School school;

  public Transcript( Student student, School school)
  {
    this.student = student;
    this.school = school;
  }

  public void printTranscript()
  {

    System.out.println("\nInitializing Transcript....\n\n\n");
    System.out.println(student.address);
    System.out.println(student.name);
    System.out.println(student.phoneNumber);
    System.out.println(student.dob);
    System.out.println(student.guardian);
    System.out.println("\n");
    System.out.println(school.name);
    System.out.println(school.address);
    System.out.println(school.phoneNumber);
    System.out.println(school.email);
    System.out.println("\n");

    int year = 9;
    for(HashMap<Course, Character> years : student.grades)
    {
      System.out.println("--------------------Grade-" + year + "-----------------" );
      System.out.println("--------Class----------------Credits--Grade--");
      for(Course course : years.keySet())
      {
        if(course.name.length() < 8)
        {
          System.out.println("\t"+course.name + "\t\t\t" +
                             course.credits + "\t" +
                             years.get(course));
        }
        else if(course.name.length() > 15)
        {
          System.out.println("\t"+course.name + "\t" +
                             course.credits + "\t" +
                             years.get(course));
        }

        else {
          System.out.println("\t"+course.name + "\t\t" +
                             course.credits + "\t" +
                             years.get(course));
        }
      }
      year++;
  }
  System.out.println("Calculated GPA over 4 years: "+ String.format("%.01f", student.calculateGPA()));
}
  public static void main(String[] args)
  {
    School kennett = new School("Kennett High School", "409 Eagles' Way, North Conway, NH", "603-356-4343", "info@khs.com");
    Student dmr84 = new Student("Dan Richardi", "123 You Wish, North Conway, NH", "555-555-5555", "01/29/1984", "Gandalf");

    ArrayList<Course> freshmanCourses = new ArrayList<Course>();
    freshmanCourses.add(new Course("English 9", 1.0f));
    freshmanCourses.add(new Course("Algebra 1", 1.0f));
    freshmanCourses.add(new Course("Biology w/lab", 1.0f));
    freshmanCourses.add(new Course("American History", 1.0f));
    freshmanCourses.add(new Course("Drawing", 1.0f));
    freshmanCourses.add(new Course("Martial Arts 1", 0.5f));
    freshmanCourses.add(new Course("MS Office", 0.5f));

    ArrayList<Course> sophomoreCourses = new ArrayList<Course>();
    sophomoreCourses.add(new Course("English 10", 1.0f));
    sophomoreCourses.add(new Course("Algebra II", 1.0f));
    sophomoreCourses.add(new Course("Chemistry w/lab", 1.0f));
    sophomoreCourses.add(new Course("World History", 1.0f));
    sophomoreCourses.add(new Course("Latin I", 1.0f));
    sophomoreCourses.add(new Course("Martial Arts II", 0.5f));
    sophomoreCourses.add(new Course("Piano", 0.5f));

    ArrayList<Course> juniorCourses = new ArrayList<Course>();
    juniorCourses.add(new Course("English 11", 1.0f));
    juniorCourses.add(new Course("Algebra II", 1.0f));
    juniorCourses.add(new Course("Marine Biology w/lab", 1.0f));
    juniorCourses.add(new Course("American Government", 0.5f));
    juniorCourses.add(new Course("Economics", 0.5f));
    juniorCourses.add(new Course("Latin II", 1.0f));
    juniorCourses.add(new Course("Web Design", 1.0f));

    ArrayList<Course> seniorCourses = new ArrayList<Course>();
    seniorCourses.add(new Course("English 12", 1.0f));
    seniorCourses.add(new Course("Calculus", 1.0f));
    seniorCourses.add(new Course("Physics w/lab", 1.0f));
    seniorCourses.add(new Course("Photography", 0.5f));
    seniorCourses.add(new Course("Yearbook", 0.5f));
    seniorCourses.add(new Course("Driver's Education", 0.5f));
    seniorCourses.add(new Course("Studio Art", 1.0f));
    seniorCourses.add(new Course("Piano", 0.5f));


    dmr84.freshmanYear = dmr84.assignGrades(freshmanCourses);
    dmr84.sophomoreYear = dmr84.assignGrades(sophomoreCourses);
    dmr84.juniorYear = dmr84.assignGrades(juniorCourses);
    dmr84.seniorYear = dmr84.assignGrades(seniorCourses);
    dmr84.compileGrades();

    Transcript dmrTrans = new Transcript(dmr84, kennett);
    dmrTrans.printTranscript();

  }
}
class School
{
  String name;
  String address;
  String phoneNumber;
  String email;

  public School( String name,
                  String address,
                  String phoneNumber,
                  String email)
  {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

}

class Student
{
  String name;
  String address;
  String phoneNumber;
  String dob;
  String guardian;

  HashMap<Course, Character> freshmanYear;
  HashMap<Course, Character> sophomoreYear;
  HashMap<Course, Character> juniorYear;
  HashMap<Course, Character> seniorYear;
  ArrayList<HashMap<Course, Character>> grades;

  public Student(String name,
                String address,
                String phoneNumber,
                String dob,
                String guardian)
  {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.guardian = guardian;

        freshmanYear = new HashMap<Course, Character>();
        sophomoreYear = new HashMap<Course, Character>();
        juniorYear = new HashMap<Course, Character>();
        seniorYear = new HashMap<Course, Character>();
        grades = new ArrayList<HashMap<Course, Character>>();
  }
  public HashMap<Course, Character> assignGrades(ArrayList<Course> courses)
  {
    Scanner input = new Scanner(System.in);
    Character grade;
    HashMap<Course, Character> finalGrades = new HashMap<Course, Character>();

    for(Course course : courses)
    {
      System.out.print("Final letter grade for " + course.name + ": ");
      grade = input.next().charAt(0);
      finalGrades.put(course, Character.toUpperCase(grade));

    }
    return finalGrades;
  }

  public float calculateGPA()
  {
    float total = 0.0f;
    int courseCount = 0;

    //Simple GPA Calculation - A = 4, B = 3, C = 2, D = 1, F = 0
    for (HashMap<Course, Character> gradeYear : grades)
    {
      for (Course course : gradeYear.keySet())
      {
        switch(gradeYear.get(course))
        {
          case 'A':
            total += 4.0f;
            courseCount++;
            break;
          case 'B':
            total += 3.0f;
            courseCount++;
            break;
          case 'C':
            total += 2.0f;
            courseCount++;
            break;
          case 'D':
            total += 1.0f;
            courseCount++;
          default:
            courseCount++;
            break;

        }
      }
    }

    return total/courseCount;
  }
  public void compileGrades()
  {
    grades.addAll(Arrays.asList(freshmanYear, sophomoreYear, juniorYear, seniorYear));
  }
}

class Course
{
  String name;
  float credits;

  public Course(String name,
                float credits)
  {
    this.name = name;
    this.credits = credits;
  }
}
