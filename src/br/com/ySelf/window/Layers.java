/*
Authors: Igor Joaquim dos Santos Lima
GitHub: https://github.com/igor036
*/
package br.com.ySelf.window;

import br.com.ySelf.Main;
import br.com.ySelf.modal.Layer;

import java.util.List;
import javax.swing.*;

public class Layers extends javax.swing.JFrame {

    private final DefaultListModel model = new DefaultListModel();

    private javax.swing.JButton btnAddLayer;
    private javax.swing.JButton btnGoToLayer;
    private javax.swing.JButton btnRemoveLayer;
    private java.awt.Choice choice1;

    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;

    private java.awt.List list1;
    private java.awt.List list2;
    private java.awt.List list3;

    public Layers() {
        initComponents();
        setLocationRelativeTo(null);
        jList.setModel(model);
    }

    public void setLayersList(List<Layer> layers) {
        // ?
        while (!model.isEmpty()) {
            model.remove(0);
        }

        for (int i = 0; i < layers.size(); i++)
            model.addElement("层" + (i + 1));

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnAddLayer = new javax.swing.JButton();
        btnRemoveLayer = new javax.swing.JButton();
        btnGoToLayer = new javax.swing.JButton();
        list1 = new java.awt.List();
        list2 = new java.awt.List();
        list3 = new java.awt.List();
        choice1 = new java.awt.Choice();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        // 该方法定义当我们点击×的时候会发生什么，发生的事情是括号内指定的参数

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("层");
        setResizable(true);

        btnAddLayer.setText("添加");
        btnAddLayer.addActionListener(new java.awt.event.ActionListener() {
            // 实现actionPerformed方法，当按钮被点击之后，执行actionPerformed
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLayerActionPerformed(evt);
            }
        });

        btnGoToLayer.setText("转到图层");
        btnGoToLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoToLayerActionPerformed(evt);
            }
        });

        // 移除选中的层
        btnRemoveLayer.setText("移除");
        btnRemoveLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLayerActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jList1);

        jList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane2.setViewportView(jList);

        // 设计界面中各按钮、信息的位置
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        // 定义水平布局
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                // 添加add按钮
                                .addComponent(btnAddLayer)
                                // 添加空隙
//                .addGap(18, 18, 18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        18, Short.MAX_VALUE)
                                // 添加转移按钮
                                .addComponent(btnGoToLayer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        18, Short.MAX_VALUE)
                                // 添加移走按钮
                                .addComponent(btnRemoveLayer))
                        .addComponent(jScrollPane2)
        );
        // 定义垂直布局
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        // 添加一个垂直高度为95的信息栏
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        // 信息栏下面加10的gap
                                        .addGap(10, 10, 10)
                                        // 下面是一层按钮
                                        .addGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout
                                                                .Alignment.BASELINE)
                                                        .addComponent(btnAddLayer)
                                                        .addComponent(btnGoToLayer)
                                                        .addComponent(btnRemoveLayer)
                                        )
                                        // 用于在组件与容器边缘之间添加一个间隙
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                        )
        );
        // 自适应调整窗口大小
        pack();
    }


    private void btnAddLayerActionPerformed(java.awt.event.ActionEvent evt) {
        Main.addLayers();
    }

    private void btnRemoveLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLayerActionPerformed
        Main.removeLayer(jList.getSelectedIndex());
    }

    private void btnGoToLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoToLayerActionPerformed
        Main.alterLayer(jList.getSelectedIndex());
    }


}
