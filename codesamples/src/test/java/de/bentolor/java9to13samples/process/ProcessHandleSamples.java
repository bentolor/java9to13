package de.bentolor.java9to13samples.process;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static java.lang.System.out;

class ProcessHandleSamples {

    /**
     * Start a process, listen to process termination, retrieve process details, kill processes.
     */
    @Test
    void testHandleAPI() throws IOException, InterruptedException {
        Process sleeper = new ProcessBuilder("sleep", "10s").start();
        ProcessHandle sleepHandle = sleeper.toHandle();

        // Perform Runnables on process exit
        sleepHandle.onExit().thenRun(         // → CompletableFuture
                () -> out.println("`sleep` process exited")
        );

        // Get info on process
        out.printf("[%d] %s - %s\n",
                   sleepHandle.pid(),
                   sleepHandle.info().user().orElse("unknown"),
                   sleepHandle.info().commandLine().orElse("n/a"));

        out.println(sleepHandle.info());
        // [user: ben, cmd: /bin/sleep, args: [10s], startTime:…, totalTime: …]

        // Find JVM process
        ProcessHandle jvmProcess = ProcessHandle.current();
        out.println(jvmProcess.info());
        // or all processes
        Stream<ProcessHandle> allProcs = ProcessHandle.allProcesses();

        // Find my direct childrens
        jvmProcess.children().forEach(out::println);

        // Kill a process
        sleepHandle.destroy();

        // Give exit handler a chance to see the sleeper onExit()
        Thread.sleep(99);
    }
}



