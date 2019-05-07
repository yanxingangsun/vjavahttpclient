/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yan; 
 
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;  
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author yanxi
 */
public class 请求记录 extends DefaultMutableTreeNode{
    //public Element n;//Node.ELEMENT_NODE
    public String 请求说明="";
    public String 请求地址="";
    public HashMap<String,String> 请求参数=new HashMap();
    
    public 请求记录(){}
    public 请求记录(Node n1){
        Element n=(Element)n1;  
        请求说明=n.getAttribute("说明"); 
        请求地址=n.getAttribute("地址");  
        
        NodeList list=n.getChildNodes();
        for(int i=0;i<list.getLength();i++){
            Node node=list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element e=(Element)node; 
                请求参数.put(e.getAttribute("名称"), e.getAttribute("内容"));
            }
        }
        
        this.setUserObject(this);
    }
    
    @Override
    public String toString(){ 
        return 请求说明;
    }
    
    public String toXMLString(){
        String s="<请求";
        s+=" 说明='"+请求说明+"'";
        s+=" 地址='"+请求地址+"'>\n";
        for(String ss:请求参数.keySet()){
            s+="<参数 名称='"+ss+"' 内容='"+请求参数.get(ss)+"'/>\n"; 
        } 
        s+="</请求>\n";  
        return s;
    } 
    
    public void 保存(主界面 f){
        this.请求说明=f.jTextField说明.getText();
        this.请求地址=f.jTextArea地址.getText();
        
        请求参数.clear();
        DefaultTableModel m=(DefaultTableModel)f.jTable参数.getModel();
        for(int i=0;i<m.getRowCount();i++){
            请求参数.put(m.getValueAt(i, 0).toString(), m.getValueAt(i, 1).toString());
        }
    }
}
