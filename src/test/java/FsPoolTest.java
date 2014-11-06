import logic.FsPool;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by nik on 06.11.14.
 */
public class FsPoolTest {
    FsPool fsPool;
    File newFile = new File("src/test/resources/5");
    @Before
    public void setUp(){
        if(newFile!=null && newFile.exists())
            newFile.delete();
        fsPool = new FsPool();
    }

    @After
    public void tearDown(){
        newFile.delete();
    }

    @Test
    public void testGettingNewFilesFromPool() throws IOException {
        File file = new File("src/test/resources");

        File[] expectedPortion1 = file.listFiles();
        List<File> portion1 = fsPool.getNewFiles(file);
        System.out.println("portion1\t" + portion1.size());
        assertEquals(3, portion1.size());
        assertTrue(Arrays.asList(expectedPortion1).containsAll(portion1));

        File[] expectedPortion2 = new File[]{};
        List<File> portion2 = fsPool.getNewFiles(file);
        System.out.println("portion2\t" + portion2.size());
        assertEquals(0, portion2.size());
        assertTrue(Arrays.asList(expectedPortion2).containsAll(portion2));

        File[] expectedPortion3 = new File[]{};
        List<File> portion3 = fsPool.getNewFiles(file);
        System.out.println("portion3\t" + portion3.size());
        assertEquals(0, portion3.size());
        assertTrue(Arrays.asList(expectedPortion3).containsAll(portion3));

        newFile.createNewFile();
        File[] expectedPortion4 = new File[]{newFile};
        List<File> portion4 = fsPool.getNewFiles(file);
        System.out.println("portion4\t" + portion4.size());
        assertEquals(1, portion4.size());
        assertTrue(Arrays.asList(expectedPortion4).containsAll(portion4));

        File[] expectedPortion5 = new File[]{};
        List<File> portion5 = fsPool.getNewFiles(file);
        System.out.println("portion5\t" + portion5.size());
        assertEquals(0, portion5.size());
        assertTrue(Arrays.asList(expectedPortion5).containsAll(portion5));

        FileUtils.writeStringToFile(newFile, "i am string!");
        File[] expectedPortion6 = new File[]{newFile};
        List<File> portion6 = fsPool.getNewFiles(file);
        System.out.println("portion6\t" + portion6.size());
        assertEquals(1, portion6.size());
        assertTrue(Arrays.asList(expectedPortion6).containsAll(portion6));
    }
}
