import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class changeAvatarModes extends Main {

    static String avatarName;
    private static File[] avatarArray = new File(userInformation.pathToAvatars).listFiles();
    private static String previousName;
    private static String currentLine;

    public static void randomChoice() {
        Random rand = new Random();
        File avatarInteger = avatarArray[rand.nextInt(avatarArray.length)];
        avatarName = avatarInteger.toString();
        if(avatarName.contains(".png") || avatarName.contains(".jpeg") || avatarName.contains(".png")) {
            setSteamAvatar.changeAvatar();
        }
        else {
            randomChoice();
        }
    }

    public static void incrementalChoice() throws IOException {
        File f = new File(userInformation.pathToAvatars+"avatar_list.txt");
        File tmp = new File(userInformation.pathToAvatars+"tmp.txt");
        if (!f.exists()) {
            FileWriter fileWriter;
            fileWriter = new FileWriter(userInformation.pathToAvatars+"avatar_list.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < avatarArray.length; i++)
                if (!avatarArray[i].isHidden()) {
                    avatarName = avatarArray[i].getName();
                    if(avatarName.contains(".png") || avatarName.contains(".jpg") || avatarName.contains(".jpeg")) {
                        printWriter.println(avatarName);
                    }
                }
            printWriter.close();
        }
        Scanner scan = new Scanner(f);
        String result;
        if (scan.hasNextLine()) {
            result = scan.nextLine();
            avatarName = result;
            previousName = avatarName;
            avatarName = userInformation.pathToAvatars + avatarName;
        }
        setSteamAvatar.changeAvatar();

        BufferedReader reader = new BufferedReader(new FileReader(userInformation.pathToAvatars+"avatar_list.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));

        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(previousName))
                continue;
            writer.write(currentLine + "\n");
        }

        writer.write(previousName);

        reader.close();
        writer.close();

        tmp.renameTo(f);
    }

}
