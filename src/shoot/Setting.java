package shoot;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Setting{
    public static int Interval;
    public static int SkySpeed;
    public static int EnemiesFrequency;
    
    public static int HeroLife;
    public static int HeroFire;
    public static int Bullet;
    
    
    public static int BeeFrequency;
    public static int BeeaddFire;
    public static int BeeXSpeed;
    public static int BeeYSpeed;
    
    public static int WaspFrequency;
    public static int WaspAddLife;
    public static int WaspXSpeed;
    public static int WaspYSpeed;
    
    public static int BigAirplaneFrequency;
    public static int BigAirplaneScore;
    public static int BigAirplaneSpeed;
    
    public static int AirplaneScore;
    public static int AirplaneSpeed;
    
    static{
        Setting json = new Setting("./env.json"); // 这里填入文件路径文件路径
        JSONObject env = json.getJson();
        
        Interval = env.getInteger("interval");
        SkySpeed = env.getInteger("skySpeed");
        
        JSONObject hero = env.getJSONObject("Hero");
        HeroLife = hero.getInteger("life");
        HeroFire = hero.getInteger("fire");
        Bullet = hero.getInteger("Bullet");
        
        JSONObject enemies = env.getJSONObject("enemies");
        EnemiesFrequency = enemies.getInteger("frequency");
        
        JSONObject bee = enemies.getJSONObject("Bee");
        BeeFrequency = bee.getInteger("frequency");
        BeeaddFire = bee.getInteger("addFire");
        BeeXSpeed = bee.getInteger("xSpeed");
        BeeYSpeed = bee.getInteger("YSpeed");
        
        JSONObject Wasp = enemies.getJSONObject("Wasp");
        WaspFrequency = Wasp.getInteger("frequency");
        WaspAddLife = Wasp.getInteger("addLife");
        WaspXSpeed = Wasp.getInteger("xSpeed");
        WaspYSpeed = Wasp.getInteger("YSpeed");
        
        JSONObject bigAirplane = enemies.getJSONObject("BigAirplane");
        BigAirplaneFrequency = bigAirplane.getInteger("frequency");
        BigAirplaneScore = bigAirplane.getInteger("score");
        BigAirplaneSpeed = bigAirplane.getInteger("Speed");
        
        JSONObject airplane = enemies.getJSONObject("Airplane");
        AirplaneScore = airplane.getInteger("score");
        AirplaneSpeed = airplane.getInteger("Speed");
    }
    
    Setting(){
    }
    
    private String file;
    
    Setting(String url){
        String charset = "utf-8";
        File file = new File(url);
        long fileByteLength = file.length();
        byte[] content = new byte[(int) fileByteLength];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = null;
        try {
            str = new String(content, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.file = str;
    }
    
    public JSONObject getJson(){
        return (JSONObject) JSONObject.parse(file);
    }
}