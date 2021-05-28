/*FloorPlanSimulator 2021/05/21 Okamoto Ryo
アプリケーションの動作を扱うmainのクラス*/

/*javaFXの各種import*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/*ファイルを扱うライブラリ*/
import java.io.File;


public class App extends Application {

    /*定数の宣言*/
    private final int APP_WIDTH = 335; //アプリ画面の横幅
    private final int APP_HEIGHT = 600; //アプリ画面の縦幅

    /*各画面のシーンを初期化
    scene1~4まで画面遷移していく*/
    public Scene scene1 = null;
    public Scene scene2 = null;
    public Scene scene3 = null;
    public Scene scene4 = null;

    /*他のクラスの宣言*/
    FileManager fileM = new FileManager(); //ファイルを扱うクラス
    PictureManager picM = new PictureManager(); //画像を扱うクラス
    
    /*変数の宣言*/
    String roomType = null; //部屋のタイプを格納(1R,1K,1DK,1LDK)
    int numLine = 0; //行数のカウントを行う
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        //ウィンドウサイズの最大最小値の設定
        stage.setMaxWidth(APP_WIDTH+13);
        stage.setMaxHeight(APP_HEIGHT+10);
        stage.setMinWidth(APP_WIDTH+13);
        stage.setMinHeight(APP_HEIGHT);

        /*初期画面にステージを渡す*/
        initScene1(stage);
        
        stage.setScene(scene1); //初期画面を最初のステージとして設定
        stage.show();
    }
    
    /*画面遷移を行うメソッド*/
    /*ステージと次の画面のシーンを受け取り、画面を変更する*/
    public void setScene(Stage stage, Scene changScene){
        stage.setScene(changScene);
        stage.show();
    }

    /********************画面1の表示************************/
    public void initScene1(Stage stage){

        /*スクリーンとして画面を生成*/
        Group screen1 = new Group();
        scene1 = new Scene(screen1, APP_WIDTH, APP_HEIGHT);
        scene1.setFill(Color.SKYBLUE);

        /*キャンバスの描画*/
        Canvas titlePicture = new Canvas(APP_WIDTH,450);
        GraphicsContext gc1 = titlePicture.getGraphicsContext2D();
        gc1.setFill(Color.BLUE);

        /*タイトル画面の画像の表示*/
        Image titleImg = new Image(new File("./picture/title.png").toURI().toString());
        gc1.drawImage(titleImg, 0, 0, titleImg.getWidth(), titleImg.getHeight(), 0, 0, 335, 450);
        screen1.getChildren().add(titlePicture);
        
        /*スタートボタンの表示*/
        BorderPane startBtn_pane = new BorderPane();
        //ボタンの位置を調整
        startBtn_pane.setPadding(new Insets(APP_HEIGHT/2+180, APP_WIDTH/2-20, 20, APP_WIDTH/2-20));
        Button startBtn = new Button("スタート");
        //ボタンの大きさを2倍
        startBtn.setScaleX(2); 
        startBtn.setScaleY(2);
        startBtn_pane.setCenter(startBtn);
        screen1.getChildren().add(startBtn_pane);

        initScene2(stage); //画面2の準備
        /*ボタンを押した際の処理*/
        startBtn.setOnMouseClicked(event -> setScene(stage, scene2)); //画面2に移行
    }

    /********************画面2の表示************************/
    public void initScene2(Stage stage){
        /*スクリーンとして画面を生成*/
        Group screen2 = new Group();
        scene2 = new Scene(screen2, APP_WIDTH, APP_HEIGHT);
        scene2.setFill(Color.SKYBLUE);

        /*レイアウトの作成*/
        GridPane gridScene2 = new GridPane();
        gridScene2.setAlignment(Pos.CENTER);
        gridScene2.setHgap(10);
        gridScene2.setVgap(10);
        gridScene2.setPadding(new Insets(25,25,25,25));
        screen2.getChildren().add(gridScene2);

        /*テキスト、ラベルの追加*/
        Text scene2Title = new Text("以下の情報を入力してください。");
        scene2Title.setFont(new Font(15));
        gridScene2.add(scene2Title, 0, 0, 2, 1);

        /*名前入力欄*/
        Label userName = new Label("名前");
        gridScene2.add(userName, 0 , 1);
        TextField userTextField = new TextField();
        gridScene2.add(userTextField, 0, 2);

        /*メールアドレス入力欄*/
        Label userMail = new Label("メールアドレス");
        gridScene2.add(userMail, 0 , 3);
        TextField userMailField = new TextField();
        gridScene2.add(userMailField, 0, 4);

        /*電話番号入力欄*/
        Label userPhone = new Label("電話番号");
        gridScene2.add(userPhone, 0 , 5);
        TextField userPhoneField = new TextField();
        gridScene2.add(userPhoneField, 0, 6);

        /*希望部屋タイプの選択欄*/
        Label userRoom = new Label("希望部屋タイプ");
        gridScene2.add(userRoom, 0 , 7);
        //ラジオボタンをグループで管理
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("1R");
        rb1.setUserData("1R");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("1K");
        rb2.setUserData("1K");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("1DK");
        rb3.setUserData("1DK");
        rb3.setToggleGroup(group);

        RadioButton rb4 = new RadioButton("1LDK");
        rb4.setUserData("1LDK");
        rb4.setToggleGroup(group);

        gridScene2.add(rb1, 0, 8);
        gridScene2.add(rb2, 1, 8);
        gridScene2.add(rb3, 0, 9);
        gridScene2.add(rb4, 1, 9);

        /*その他要望記入欄*/
        GridPane gridScene2Bottom = new GridPane();
        gridScene2Bottom.setAlignment(Pos.CENTER);
        gridScene2Bottom.setHgap(10);
        gridScene2Bottom.setVgap(10);
        gridScene2Bottom.setTranslateY(320);
        gridScene2Bottom.setTranslateX(25);
        screen2.getChildren().add(gridScene2Bottom);

        //ラベルとテキストエリアの追加
        Label userInfo = new Label("その他要望");
        gridScene2Bottom.add(userInfo, 0 , 0);

        TextArea userInfoField = new TextArea();
        userInfoField.setMaxWidth(250);
        userInfoField.setMaxHeight(100);
        gridScene2Bottom.add(userInfoField, 0, 1);

        /*決定ボタンの表示*/
        BorderPane scene2Btn_pane = new BorderPane();
        //ボタンの位置を調整
        scene2Btn_pane.setTranslateX(APP_WIDTH/2-20);
        scene2Btn_pane.setTranslateY(APP_HEIGHT/2+180);
        Button scene2Btn = new Button("決定");
        //ボタンの大きさを2倍
        scene2Btn.setScaleX(2); 
        scene2Btn.setScaleY(2);
        scene2Btn_pane.setCenter(scene2Btn);
        screen2.getChildren().add(scene2Btn_pane);

        /*ボタンを押したときの処理*/
        scene2Btn.setOnMouseClicked((event) -> {
            //FileManagerクラスにアクセスし、ユーザーデータの保存をする
            fileM.saveUserData(userTextField.getText(), userMailField.getText(), userPhoneField.getText(),
            group.getSelectedToggle().getUserData().toString(), userInfoField.getText());
            //ラジオボタンで決まった値を部屋タイプとして格納
            roomType = group.getSelectedToggle().getUserData().toString();
            
            initScene3(stage); //画面3の準備
            setScene(stage, scene3); //画面3へ移行
        });
    }

    /********************画面3の表示************************/
    public void initScene3(Stage stage){
        /*スクリーンとして画面を生成*/
        Group screen3 = new Group();
        scene3 = new Scene(screen3, APP_WIDTH, APP_HEIGHT);
        scene3.setFill(Color.SKYBLUE);

        /*テキスト、ラベルの追加*/
        Text scene3Title = new Text("好きな間取りを選択してください。");
        scene3Title.setFont(new Font(15));
        scene3Title.setTranslateX(25);
        scene3Title.setTranslateY(41);
        screen3.getChildren().add(scene3Title);

        /*キャンバスの描画*/
        Canvas floorPicture = new Canvas(APP_WIDTH, APP_HEIGHT/2+150);
        GraphicsContext gc3 = floorPicture.getGraphicsContext2D();
        gc3.translate(0, 60);
        gc3.setFill(Color.BLUE);

        /*間取りの画像の表示*/
        fileM.readFile(roomType); //部屋のタイプに合わせたファイルの読み込み
        
        for(int i = 0; i < fileM.fileNumLength/5; i++){
            //FileManagerクラスの配列内の値からPictureManagerクラスのが画像を決定
            Image floorImg = new Image(new File(picM.changePicture(fileM.data[numLine][5*i+1])).toURI().toString());
            //キャンバスに画像を表示、ファイル内の数値で座標を指定
            gc3.drawImage(floorImg, 0, 0, floorImg.getWidth(), floorImg.getHeight(), Double.parseDouble(fileM.data[numLine][5*i+2]), Double.parseDouble(fileM.data[numLine][5*i+3]), Double.parseDouble(fileM.data[numLine][5*i+4]), Double.parseDouble(fileM.data[numLine][5*i+5]));
        }
        screen3.getChildren().add(floorPicture);

        /*レイアウトの作成*/
        GridPane gridScene3 = new GridPane();
        gridScene3.setAlignment(Pos.CENTER);
        gridScene3.setHgap(50);
        gridScene3.setVgap(10);
        gridScene3.setTranslateY(APP_HEIGHT/2+180);
        gridScene3.setTranslateX(APP_WIDTH/3-10);
        screen3.getChildren().add(gridScene3);
        //ボタンをレイアウトに合わせて配置
        Button scene3BtnNext = new Button("次へ");
        Button scene3BtnInput = new Button("決定");
        //ボタンの大きさを2倍
        scene3BtnNext.setScaleX(2); 
        scene3BtnNext.setScaleY(2);
        scene3BtnInput.setScaleX(2);
        scene3BtnInput.setScaleY(2);
        gridScene3.add(scene3BtnNext, 0, 0);
        gridScene3.add(scene3BtnInput, 1, 0);

        /*「次へ」ボタンを押したときの処理*/
        scene3BtnNext.setOnMouseClicked((event) -> {
            numLine += 1; //行数のカウントをする
            //ファイルの行を超えるならカウントを0に
            if(numLine >= fileM.fileNumLines){
                numLine = 0;
            }
            gc3.clearRect(0, 0, APP_WIDTH, APP_HEIGHT/2+150);
            //ファイルの次の行の間取り情報を描画する
            for(int i = 0; i < fileM.fileNumLength/5; i++){
                Image floorImg = new Image(new File(picM.changePicture(fileM.data[numLine][5*i+1])).toURI().toString());
                gc3.drawImage(floorImg, 0, 0, floorImg.getWidth(), floorImg.getHeight(), Double.parseDouble(fileM.data[numLine][5*i+2]), Double.parseDouble(fileM.data[numLine][5*i+3]), Double.parseDouble(fileM.data[numLine][5*i+4]), Double.parseDouble(fileM.data[numLine][5*i+5]));
            }
        });
        
        /*「決定」ボタンを押したときの処理*/
        scene3BtnInput.setOnMouseClicked((event) -> {
            initScene4(stage); //画面4の準備
            setScene(stage, scene4); //画面4へ移行
        });
        
    }

    /********************画面4の表示************************/
    public void initScene4(Stage stage){
        /*スクリーンとして画面を生成*/
        Group screen4 = new Group();
        scene4 = new Scene(screen4, APP_WIDTH, APP_HEIGHT);
        scene4.setFill(Color.SKYBLUE);

        /*キャンバスの描画*/
        Canvas totalPicture = new Canvas(APP_WIDTH, APP_HEIGHT);
        GraphicsContext gc4 = totalPicture.getGraphicsContext2D();
        screen4.getChildren().add(totalPicture);
            
        /*ファイル内の文字を出力*/
        gc4.setFont(new Font(25));
        gc4.setFill(Color.BLUE);
        gc4.setTextAlign(TextAlignment.CENTER);
        gc4.fillText("FloorPlanSimulator", APP_WIDTH/2, 25);
        
        //FileManagerにアクセスし、ユーザーデータをテキストとして出力
        gc4.setFont(new Font(15));
        gc4.setTextAlign(TextAlignment.LEFT);
        gc4.setFill(Color.BLACK);
        gc4.fillText(fileM.userData[0], 30, 50);
        gc4.fillText(fileM.userData[1], 30, 70);
        gc4.fillText(fileM.userData[2], 30, 90);
        gc4.fillText(fileM.userData[3], 30, 110);
        gc4.fillText(fileM.userData[4], 30, 130);

        /*間取りの画像の表示*/
        for(int i = 0; i < fileM.fileNumLength/5; i++){
            Image floorImg = new Image(new File(picM.changePicture(fileM.data[numLine][5*i+1])).toURI().toString());
            gc4.drawImage(floorImg, 0, 0, floorImg.getWidth(), floorImg.getHeight(), Double.parseDouble(fileM.data[numLine][5*i+2]), Double.parseDouble(fileM.data[numLine][5*i+3])+150, Double.parseDouble(fileM.data[numLine][5*i+4]), Double.parseDouble(fileM.data[numLine][5*i+5]));
            
        }

        /*レイアウトの作成*/
        GridPane gridScene4 = new GridPane();
        gridScene4.setAlignment(Pos.CENTER);
        gridScene4.setHgap(50);
        gridScene4.setVgap(10);
        gridScene4.setTranslateY(APP_HEIGHT-45);
        gridScene4.setTranslateX(APP_WIDTH/3-10);
        screen4.getChildren().add(gridScene4);

        //ボタンをレイアウトに合わせ生成
        Button scene4BtnDownload = new Button("出力");
        Button scene4BtnClose = new Button("終了");
        //ボタンの大きさを変更
        scene4BtnDownload.setScaleX(1.8); 
        scene4BtnDownload.setScaleY(1.8);
        scene4BtnClose.setScaleX(1.8);
        scene4BtnClose.setScaleY(1.8);
        gridScene4.add(scene4BtnDownload, 0, 0);
        gridScene4.add(scene4BtnClose, 1, 0);
        
        /*「出力」ボタンを押したときの処理*/
        scene4BtnDownload.setOnMouseClicked((event) -> {
            //png画像としてキャンバス内の画像を出力
            WritableImage img = totalPicture.snapshot(new SnapshotParameters(), null);
            fileM.outPutFile(img, "floorPlanData", "png");
        });

        /*ボタンを押したときの処理*/
        scene4BtnClose.setOnMouseClicked((event) -> {
            //ファイルのアップデートを行う
            fileM.updateFile(numLine);
            stage.close(); //アプリケーションの終了
        });
    }
}