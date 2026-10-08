# Q13: Patient Appointment

Simple **Java + XML** solution for the MCA Android lab. Activities: MainActivity, DashboardActivity.

## Run in Android Studio
1. Create **Empty Views Activity**, Language **Java**, package **`com.example.labapp`**.
2. From this question's folder, copy `app/src/main/java`, `app/src/main/res/layout`, and `app/src/main/AndroidManifest.xml` into the generated project (replace/merge as needed).
3. Keep the included Action Bar theme (Options Menu questions need it); run on an emulator or physical Android device.
4. No Firebase or external libraries are needed.

Enter patient name, age, token or date and submit. The Options Menu clears the form. The dashboard lists specialists, confirms a sample appointment with AlertDialog + Toast, and long-pressing opens an external hospital-route navigation link. No actual appointment is booked.

These are offline **lab demonstrations**, not production-grade apps. `SharedPreferences` is private application storage, but is **not encrypted**.
