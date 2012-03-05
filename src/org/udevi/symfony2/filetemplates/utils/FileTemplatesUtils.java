/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.udevi.symfony2.filetemplates.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author tpoperecinii
 */
public class FileTemplatesUtils {
    public static final String BUNDLE = "Bundle";
    public static final String REPOSITORY = "Repository";

    public static final String SRC = "src";
    public static final String WINDOWS_SEPARATOR = "/";
    public static final String UNIX_SEPARATOR = "\\";

    public static boolean isNullOrEmpty(String val) {
        return val == null || val.trim().length() == 0;
    }

    public static boolean isNullOrEmpty(Collection<?> values) {
        if (values == null || values.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String tableize(String targetName) {
        String[] arrayOfWords = StringUtils.splitByCharacterTypeCamelCase(targetName);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arrayOfWords.length; i++) {
            String word = arrayOfWords[i];

            if (i > 0) {
                builder.append("_");
            }

            builder.append(word.toLowerCase());
        }

        return builder.toString();
    }

    /**
     * Build a relative path to the given base path. Example:
     *
     * path = "/var/data/stuff/xyz.dat"; base = "/var/data"; relative ==
     * "stuff/xyz.dat";
     *
     * @param base - the path used as the base
     * @param path - the path to compute relative to the base path
     * @return A relative path from base to path
     * @throws IOException
     */
    public static String findRelativePath(String absolutePath, String path)
            throws IOException {
        String relative = new File(path).toURI().relativize(new File(absolutePath).toURI()).getPath();

        return relative;
    }

    public static String getNamespaceForPhp(String absolutePath) throws IOException {
        String namespacePath = absolutePath;

        int closestSourceIndex = absolutePath.lastIndexOf(SRC);

        if (closestSourceIndex > -1) {
            String uriSourcePackage = absolutePath.substring(0, closestSourceIndex + SRC.length());

            namespacePath = findRelativePath(absolutePath, uriSourcePackage);
        }

        namespacePath = namespacePath.replace(WINDOWS_SEPARATOR, UNIX_SEPARATOR);

        return namespacePath;
    }

   public static String getNamespaceForRepository(String namespace) {
        String namespaceRepository = namespace;
       
        int closestBundleIndex = namespace.lastIndexOf(BUNDLE);

        if (closestBundleIndex > -1) {
            String uriBundle = namespace.substring(0, closestBundleIndex + BUNDLE.length());

            namespaceRepository = FilenameUtils.concat(uriBundle, REPOSITORY);
        }

        return namespaceRepository;
    }
}
