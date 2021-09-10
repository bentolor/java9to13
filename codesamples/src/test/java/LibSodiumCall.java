import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.SymbolLookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

public class LibSodiumCall {

    private static final SymbolLookup libsodiumLookup;
    static {
        System.load("/usr/lib/x86_64-linux-gnu/libsodium.so.23");
        libsodiumLookup = SymbolLookup.loaderLookup();
    }

    public static void main(String... args) throws Throwable {
        MethodHandle crypto_box_sealbytes =
                CLinker.getInstance()
                        .downcallHandle(
                                libsodiumLookup.lookup("crypto_box_sealbytes").get(),
                                MethodType.methodType(int.class),
                                FunctionDescriptor.of(CLinker.C_INT)
                        );

        var crypto_box_SEALBYTES = (int) crypto_box_sealbytes.invokeExact();
        System.out.println(crypto_box_SEALBYTES);
    }
}
