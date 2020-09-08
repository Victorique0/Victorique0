package Person;
//角色对象的功能：存储位置信息，接受键盘信息并且响应，能够绘制
import Dir.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import window.*;
import Wall.*;
public class poi extends Rectangle implements Runnable{
    private Image img = Toolkit.getDefaultToolkit().getImage("src/Person/1.PNG");
    private int speed;
    private dir direction;
    private begin jp;
    private boolean move;
    private wall_list w_list;
    private boolean fire;
    private int pre_x;
    private int pre_y;
    private int HP;
    private Boolean allow_run;
    public poi(int x_, int y_,begin jp_,wall_list w_list_)
    {
        w_list = w_list_;
        jp = jp_;
        x = x_;
        y = y_;
        pre_x = x_;
        pre_y = y_;
        speed = 10;
        direction = dir.UP;
        move = false;
        width = 119;
        height = 150;
        fire = false;
        HP = 5;
        allow_run = true;
    }

    public void run(){
        while(allow_run){
            try{
                //System.out.println("进入夕立线程 ");
                //System.out.println(HP);
                Thread.sleep(10);
                this.update();
            }catch (InterruptedException e){ }
        }
    }

    public void set_run(Boolean b){
        allow_run = b;
    }

    public void dec_HP()
    {
        HP -= 1;
    }
    public int get_HP(){
        return HP;
    }

    public boolean HPisZero(){
        if(HP==0)
            return true;
        return false;
    }

    public void paint(Graphics g) {
        g.drawImage(img,x,y,width,height,jp);
    }

    public void update_pressed(KeyEvent e)
    {
        move = true;
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            direction = dir.UP;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            direction = dir.DOWN;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            direction = dir.LEFT;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            direction = dir.RIGHT;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            //jp.add_bullet(x+60,y,direction);
            //fire = true;
            move = false;
        }
    }

    public void update_released(KeyEvent e)
    {
        move = false;
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
            fire = true;
    }

    public void update(){
        if(fire)
        {
            jp.add_bullet(x+60,y,direction,true);
            System.out.println("夕立发射子弹");
            fire = false;
        }
        if(move==false)
            return;
        pre_x = x;
        pre_y = y;
        if(direction==dir.UP&&(y-speed)>=0)
            y -= speed;
        if(direction==dir.DOWN&&(y+speed)<=1000-height)
            y += speed;
        if(direction==dir.LEFT&&(x-speed)>=0)
            x -= speed;
        if(direction==dir.RIGHT&&(x+speed)<=1500-width)
            x += speed;
    }
    public void go_back()
    {
        x = pre_x;
        y = pre_y;
    }

}
