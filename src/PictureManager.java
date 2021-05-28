/*PictureManager(画像の扱いを管理するクラス)*/

public class PictureManager {
    /*引数によってどの画像のURLを取得するか決めるメソッド*/
    public String changePicture(String pictureType){
       String urlPicture = null;
       switch(pictureType){
           case "1": urlPicture = "./picture/room.png"; break;
           case "2": urlPicture = "./picture/entrance.png"; break;
           case "3": urlPicture = "./picture/kitchen.png"; break;
           case "4": urlPicture = "./picture/bath.png"; break;
           case "5": urlPicture = "./picture/toilet.png"; break;
           case "6": urlPicture = "./picture/washroom.png"; break;
           case "7": urlPicture = "./picture/closet.png"; break;
           case "8": urlPicture = "./picture/border.png"; break;
           case "9": urlPicture = "./picture/door.png"; break;
       }
    return urlPicture;
   }
}
