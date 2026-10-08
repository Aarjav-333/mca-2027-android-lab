# Question 10: Placement Coordination

This folder contains Java + XML source files for **one separate Android Studio project**.

1. Start Android Studio -> New Project -> Empty Views Activity -> Language Java.
2. Use package name `com.example.labapp` (or change the `package` declaration in each Java file to match your project).
3. Copy the Java files into `app/src/main/java/com/example/labapp/`.
4. Copy layout XML into `app/src/main/res/layout/`.
5. Replace `app/src/main/AndroidManifest.xml` with this sample, or merge the declared activities with your existing manifest.
6. No Firebase, external library, or database setup is required.

Activities: MainActivity, DrivesActivity.

Select Student or Officer (demo roles, no authentication). Officer menu posts drives. Students select drive, enter CGPA, check both prerequisite boxes and apply. Company, CGPA cutoff and applicant counts are stored in SharedPreferences. Applicants are counted per submission (duplicates are possible in this lab sample).
