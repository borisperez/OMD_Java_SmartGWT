/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

//import com.smartgwt.client.data.DataSource;
import br.com.etlplus.client.objects.Env;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;

public class EnvironmentTreeGrid extends TreeGrid {
    
    public EnvironmentTreeGrid(Env[] envs) {
        setHeight100();
        final Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setNameProperty("EnvironmentName");
        tree.setIdField("EnvId");
        tree.setParentIdField("EnvParentId");
        tree.setShowRoot(true);
        TreeNode[] treeNode = new TreeNode[envs.length];
        for (int i = 0; i < envs.length; i++) {
            EnvironmentTreeGrid.EnvTreeNode envTreeNode = new EnvironmentTreeGrid.EnvTreeNode(envs[i].getEnvId(), envs[i].getEnvParentId(), envs[i].getEnvironmentName(), envs[i].getSeverName());
            treeNode[i] = envTreeNode;
        }
        tree.setData(treeNode);
        setShowHeader(false);
        setLeaveScrollbarGap(false);
        setAnimateFolders(true);
        setCanAcceptDroppedRecords(false);
        setCanReparentNodes(false);
        setSelectionType(SelectionStyle.SINGLE);
        setShowAllRecords(true);
        setLoadDataOnDemand(false);
        setCanSort(false);
        setData(tree);
        
        
    }
    
    public static final class EnvTreeNode extends TreeNode {
        
        public EnvTreeNode(int EnvId, int EnvParentId, String EnvironmentName, String SeverName) {
            setEnvId(EnvId);
            setEnvParentId(EnvParentId);
            setEnvironmentName(EnvironmentName);
            setSeverName(SeverName);
        }
        
        public void setEnvId(int value) {
            setAttribute("EnvId", value);
        }
        
        public void setEnvParentId(int value) {
            setAttribute("EnvParentId", value);
        }
        
        public void setEnvironmentName(String name) {
            setAttribute("EnvironmentName", name);
        }
        
        public void setSeverName(String name) {
            setAttribute("SeverName", name);
        }
    }
}
