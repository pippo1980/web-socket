-injars 'message-server-socket-1.1-distribution/message-server-socket-1.1/classes'
-outjars 'message-server-socket-1.1-distribution/message-server-socket-1.1/test-classes'

-libraryjars <java.home>/lib/rt.jar
-libraryjars 'message-server-socket-1.1-distribution/message-server-socket-1.1/lib'
-libraryjars 'lib'

-target 1.6
-optimizations 'method/propagation/*,method/inlining/*'
-keepattributes *Annotation*
-verbose
-dontnote


# Keep - Applications. Keep all application classes, along with their 'main'
# methods.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Keep - Library. Keep all public and protected classes, fields, and methods.
-keep public class * {
    public protected <fields>;
    public protected <methods>;
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Also keep - Serialization code. Keep all fields and methods that are used for
# serialization.
-keepclassmembers class * extends java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Also keep - Bean classes. Keep all specified classes, along with their getters
# and setters.
-keep class * {
    void set*(***);
    void set*(int,***);
    boolean is*();
    boolean is*(int);
    *** get*();
    *** get*(int);
}

# Also keep - Database drivers. Keep all implementations of java.sql.Driver.
-keep class * extends java.sql.Driver

# Keep names - Native method names. Keep all native class/method names.
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}
