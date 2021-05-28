/*FileManager(ファイルの扱い、入出力を管理するクラス)*/

/*ファイルの扱いをするライブラリ*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*JavaFXライブラリ*/
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class FileManager {
    
    String[][] data = null; //ファイルの間取り情報を格納する
    String[] userData = null; //ユーザが入力したデータを格納する
    int fileNumLength = 0; //ファイル内の1行の長さ
    int fileNumLines = 3; //ファイル内の行数
    String file_name = null; //ファイル名

    /*ユーザーが選んだ部屋タイプからその間取り情報をファイルから読み込むメソッド*/
    public void readFile(String room){
        BufferedReader br = null;
        
        //ファイルの選択
        switch(room){
            case "1R" : file_name = "./file/1R.csv"; fileNumLength = 26; break;
            case "1K" : file_name = "./file/1K.csv"; fileNumLength = 36; break;
            case "1DK" : file_name = "./file/1DK.csv"; fileNumLength = 41; break;
            case "1LDK" : file_name = "./file/1LDK.csv"; fileNumLength = 46; break;
        }
        //ファイル内の情報をdata配列に格納する
        data = new String[fileNumLines][fileNumLength];
        
            try{
            File file = new File(file_name);
            br = new BufferedReader(new FileReader(file));

            int index = 0;
            String line;
            while ((line = br.readLine()) != null){
                data[index] = line.split(",");
                index++;
            }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
    }

    /*間取り情報をランキングを管理するメソッド*/
    public void updateFile(int count){
        String tmp; //一時格納用変数
        //ユーザーの選択した間取りに対し、ランクポイントが1上昇
        data[count][0] = String.valueOf(Integer.valueOf(data[count][0]) + 1);
        //ランクポイントがある程度溜まったら、一括して数値を下げる
        for(int i = 0; i < fileNumLines; i++){
            if(Integer.valueOf(data[count][0])  >= 100){
                data[i][0] = String.valueOf(Integer.valueOf(data[i][0]) * 0.5);
            }
        }
        /*間取り情報をポイント降順にソート*/
        for(int j = 0; j < fileNumLines; j++){
            for(int i = 0; i < fileNumLines; i++){
                if(Integer.valueOf(data[i][0]) < Integer.valueOf(data[j][0])){
                    for(int k = 0; k < fileNumLength; k++){
                        tmp = data[i][k];
                        data[i][k] = data[j][k];
                        data[j][k] = tmp;
                    }
                }
            }
        }
        //ファイルに出力する
        try{
            FileWriter fw = new FileWriter(file_name);
            for(int j = 0; j < fileNumLines; j++){
                for(int i = 0; i < fileNumLength; i++){
                    fw.write(data[j][i]);
                    fw.write(",");
                }
                fw.write("\n");
            }
            fw.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }


    }
    /*ユーザーのデータを配列に格納するメソッド*/
    public void saveUserData(String name, String mail, String phone, String room, String others){
        userData = new String[5];
        userData[0] = "名前:" + name + "\n";
        userData[1] = "メールアドレス:" + mail + "\n";
        userData[2] = "電話番号:" + phone + "\n";
        userData[3] = "部屋タイプ:" + room + "\n";  
        userData[4] = "その他要望:" + others + "\n";
    }
    /*キャンバスの画像をpngとして出力する*/
    public void outPutFile(WritableImage img, String filename, String ext){
        File f = new File("./output/"+ filename + "." + ext );
        try{
            ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", f);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
    }
}
