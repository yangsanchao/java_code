package com.yangsc.word;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * <p>Description: WordWrap</p>
 *
 * <p>Copyright: © 2018-至今 .All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2019-09-23 19:35:35
 **/
public class WordWrap {


    public WordWrap() {
    }

    public static final char CHAR_EMPTY = ' ';
    public static final String BLANK_SPACE = " ";


    /**
     * Overloaded method called when just the fullText and line size are known
     * @param strFullText String
     * @param iLineSize int
     * @return ArrayList
     */
    public static String formatLine(String strFullText,
                                            int iLineSize) {
        ArrayList arrayList  = formatLineBreak(strFullText, iLineSize, -1);
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer index = 0;index < arrayList.size();index ++) {
            if (index != 0) {
                stringBuilder.append(" ");
            }
            Object object = arrayList.get(index);
            String string = object.toString();
            stringBuilder.append(string);
            stringBuilder.append(offsetStr(iLineSize - string.length()));
        }
        return stringBuilder.toString();
    }


    private static  String offsetStr(Integer length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Overloaded method called when just the fullText and line size are known
     * @param strFullText String
     * @param iLineSize int
     * @return ArrayList
     */
    public static ArrayList formatLineBreak(String strFullText,
                                            int iLineSize) {
        return formatLineBreak(strFullText, iLineSize, -1);
    }

    /**
     * Overloaded method called when just the fullText, line size and totalbodylength
     * are known
     * @param strFullText String
     * @param iLineSize int
     * @param iTotalBodyLen int
     * @return ArrayList
     */
    public static ArrayList formatLineBreak(String strFullText,
                                            int iLineSize,
                                            int iTotalBodyLen) {
        return formatLineBreak(strFullText, -1, null, false, iLineSize,
                iTotalBodyLen);
    }

    /**
     *
     * @param strFullText String - this includes header and msg body
     *
     * @param iHeaderLen int -(optional) if there is no header pass value as -1
     *
     * @param strSeparator String - (optional) if there is no header(indicated by
     * iHeaderLen=-1) anything passed here is ignored. If there is a header pass
     * the separator in form on String, ex: ":" or "-" to indicate end of header
     *
     * @param bAddSeparatorToHeader boolean - if true separator is added to end of
     * header else separator is removed from the header
     *
     * @param iEachLineInBodyLen int - the text will be wrapped to n
     * number of chars as mentioned by this argument. Words will be wrapped to
     * next line if the  complete word doesn't fit in to this line.
     *
     * @param iTotalBodyLen int - (optional) Required total length of msg body - if
     * there is no requirement for constraining total length pass this as -1
     *
     * @return ArrayList - the first entry will be msg header. If there is no
     * no requirement for msg header (indicated by iHeaderLen=-1) then the first
     * entry in ArrayList will be an empty string.
     */
    public static ArrayList formatLineBreak(String strFullText, int iHeaderLen,
                                            String strSeparator,
                                            boolean bAddSeparatorToHeader,
                                            int iLineSize,
                                            int iTotalBodyLen) {
        ArrayList<String> lstResultStrings = new ArrayList<String> ();
        if (strFullText == null || iLineSize <= 0) {
            System.out.println("The text is null or iLineSize is <= 0 ");
            return lstResultStrings;
        }
        String msgHdr = "";
        String msgBody = "";
        int posColon = -1;

        if (strFullText != null) {
            //If there is a header
            if (iHeaderLen > 0) {

                if (strSeparator == null) {
                    System.out.println("The text includes a header but no separator has"
                            + " been provided to identify the header");
                    return lstResultStrings;
                }
                //determining the position of the column which separates the header and body
                posColon = (strFullText.indexOf(strSeparator));
                //extracting header till the position of the colon
                msgHdr = strFullText.substring(0, posColon + 1).trim();
                if (msgHdr.length() >= iHeaderLen && bAddSeparatorToHeader) {
                    //if header is greater than iHeaderLen trim the header to a length of
                    //iHeaderLen-1 and add separator
                    msgHdr = lengthenStr(msgHdr, CHAR_EMPTY, iHeaderLen - 1, false);
                    msgHdr = msgHdr + strSeparator;
                }
                else {
                    //lengthen/shorten the header to iHeaderLen characters
                    msgHdr = lengthenStr(msgHdr, CHAR_EMPTY, iHeaderLen, false);
                }
                lstResultStrings.add(msgHdr);
            }

            msgBody = strFullText.substring(posColon + 1).trim();

            //truncating or lengthening the message body to iTotalBodyLen characters
            if (iTotalBodyLen > 0) {
                msgBody = lengthenStr(msgBody, CHAR_EMPTY, iTotalBodyLen, false);
            }
            //Number of chars in the body
            int iBodyLength = msgBody.trim().length();
            int iStartIndex = 0;
            int iEndIndex = iLineSize;
            String strTemp = null;
            boolean bLoop = true;

            while (bLoop) {
                // If endIndex greater than body length put rest of String in ArrayList
                // and exit loop
                if (iEndIndex >= iBodyLength) {
                    lstResultStrings.add(msgBody.substring(iStartIndex).trim());
                    bLoop = false;
                    continue;
                }

                if (msgBody.charAt(iStartIndex) == ' ') {
                    iStartIndex++;
                    iEndIndex++;
                }

                strTemp = msgBody.substring(iStartIndex, iEndIndex);

                int iLastSpaceIndex = strTemp.lastIndexOf(BLANK_SPACE);

                //if last char in current string or first char of next line is ' '
                //then add string to arraylist
                //Also if there is no space in the current line then blindly add it
                if (iLastSpaceIndex == -1 || msgBody.charAt(iEndIndex - 1) == ' '
                        || msgBody.charAt(iEndIndex) == ' ') {
                    lstResultStrings.add(msgBody.substring(iStartIndex, iEndIndex).trim());
                    iStartIndex = iEndIndex;
                    iEndIndex = iEndIndex + iLineSize;
                }
                //if word is being cut then exclude that word from current line
                //so that it can be included in the next line
                else {
                    lstResultStrings.add(msgBody.substring(iStartIndex,
                            iStartIndex + iLastSpaceIndex +
                                    1).trim());
                    iStartIndex = iStartIndex + iLastSpaceIndex + 1;
                    iEndIndex = iStartIndex + iLineSize;
                }

                //If startIndex is greater than body length then exit loop
                if (iStartIndex > iBodyLength) {
                    bLoop = false;
                    continue;
                }
            }

        }
        return lstResultStrings;
    }

    /**
     * Method to lengthen or shorten a given String. If the parameter String is
     * longer than the required length, then this methods cuts the String from right.
     * If the parameter String is shorter than required length then it adds the passed
     * character to left or right to fit the required length.
     * @param str String - Given String
     * @param addChar char - Character to add
     * @param length int - Required length of String
     * @param addLeft boolean - True if characters are to be added to the left, false
     *                         otherwise
     * @return String - The String of required length
     */
    public static String lengthenStr(String str, char cAddChar, int iLength,
                                     boolean bAddLeft) {
        StringBuffer strBuffResult = new StringBuffer(iLength);
        if (str != null) {
            if (str.length() > iLength) {
                strBuffResult.append(str.substring(0, iLength));
            }
            else {
                strBuffResult.append(str);
            }
        }
        int countAddChars = iLength - strBuffResult.length();
        if (countAddChars > 0) {
            char[] addedChars = new char[countAddChars];
            Arrays.fill(addedChars, cAddChar);
            if (bAddLeft) {
                strBuffResult.insert(0, addedChars);
            }
            else {
                strBuffResult.append(addedChars);
            }
        }
        return strBuffResult.toString();
    }

    public static void main(String[] args) {
        String str = "hello you jiu shi zui hao de ma haha";
        ArrayList arrayList = WordWrap.formatLineBreak(str, 7);
        for (Object var : arrayList) {
            System.out.println(var.toString());
        }
        String line = WordWrap.formatLine(str, 7);
        System.out.println(line);
    }

}