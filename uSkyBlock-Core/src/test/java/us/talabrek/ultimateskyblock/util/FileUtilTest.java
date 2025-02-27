package us.talabrek.ultimateskyblock.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * JUnit tests for FileUtil
 */
public class FileUtilTest {
    @Test
    public void testGetExtension() {
        assertThat(FileUtil.getExtension("basename.ext"), is("ext"));
        assertThat(FileUtil.getExtension("my file.with.dot.yml"), is("yml"));
    }
}
