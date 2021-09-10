
import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;

import java.lang.invoke.MethodType;

public class ForeignAPI {
    public static void main(String... p) throws Throwable {
        // Adressiertes Symbol; hier via Lookup in C stdlib
        var libSymbol = CLinker.systemLookup().lookup("getpid").get();
        // Gewünschte Java-Signatur des Java Foreign Handles
        var javaSignature = MethodType.methodType(long.class);
        // Signatur der aufzurufenden C-Funktion
        var nativeSignature = FunctionDescriptor.of(CLinker.C_LONG);

        // Abstraction für C ABI Konventionen
        CLinker cABI = CLinker.getInstance();
        var getpid = cABI.downcallHandle(
                libSymbol, javaSignature, nativeSignature
        );
        System.out.println((long) getpid.invokeExact());

        System.out.println(ProcessHandle.current().pid());
    }
}
