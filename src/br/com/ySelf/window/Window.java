/*
Authors: Igor Joaquim dos Santos Lima
GitHub: https://github.com/igor036
 */
package br.com.ySelf.window;

import br.com.ySelf.modal.EColor;
import br.com.ySelf.util.MatUtil;
import br.com.ySelf.util.Text;

import java.awt.*;

import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;
import javax.swing.event.*;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

public class Window extends javax.swing.JFrame {

    //control of photo
    private Mat img;              //actually
    private Mat originalImg;
    private String originalImgPath;
    private Mat temp;             //temp
    private static Mat copy;
    private Stack<Mat> last; //ctrl+z
    private Stack<Mat> next;     //ctrl+y


    //glitch wave 毛刺波？
    private int waveLength;
    private EColor color;

    //widget 小部件
    // pending 小组件大小缩放
    private String widgetPath;
    private final List<String> WIDGET_SUPPORT_FILE_TYPES;
    private final List<JLabel> widgetLabelList;

    //outer's
    private int selectedRegionX, selectedRegionY;
    private Mat copyRegionMat;           //copy region
    private Mat paintingImg;            //image paint
    private Mat nexLayerImg;           //image use to paint
    private Mat matZoomOut;
    private Mat matZoomOutNexLayerImg;
    private int lastSaturation;

    private JPanel zoomRegion;
    private Text text;

    private javax.swing.JDialog Text;
    public static JRadioButton red;
    public static JRadioButton blue;
    public static JRadioButton green;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // 点击工具后出现的菜单栏
    // 一个框架？Img需要和lPhoto相关联


    private JMenuItem blur;
    private JSlider brightnessSlider;
    private JMenu btMasks;
    private JButton btResize;
    private JButton btnVhs;
    private JMenuItem cartoon;
    private ButtonGroup colors;
    private ButtonGroup vhs;

    private boolean flag;
    // 总画板
    // 作用？
    private JPanel panel;
    // 总菜单栏
    private JMenuBar menuBar;
    // 图片显示区域
    private JLabel showImgRegionLabel;

    private JMenu openPhotoMenu;
    // options
    private JMenu optionsMenu;
    private JMenuItem optionsMenuUndoItem;
    private JMenuItem optionsMenuRedoItem;
    private JMenuItem optionsMenuWidgetItem;
    private JLabel selectedWidgetLabel;
    private JMenuItem optionsMenuCopyItem;
    private boolean pasting = false;
    private JMenuItem optionsMenuCutItem;
    private JMenuItem optionsMenuSaveItem;
    private JMenuItem writeText;
    private JMenuItem zoomIn;
    private JMenuItem zoomOut;
    private JMenuItem focus;


    // Glitch
    private JMenu glitchMenu;
    private JMenuItem glitchMenuWaveItem;
    private JMenuItem glitchMenuVHSItem;
    private JMenuItem glitchMenuAddItem;
    private JMenuItem glitchMenuLilacItem;
    private JDialog glitchMenuLilacItemDialog;
    private JDialog glitchMenuWaveItemDialog;
    private JDialog glitchMenuVHSItemDialog;

    // Tool
    private JMenu toolMenu;
    private JCheckBoxMenuItem toolMenuSelectRegionItem;
    private JLabel selectedRegionLabel;
    // 当点击Pen时，该实例化对象的isSelected变为true
    private JCheckBoxMenuItem toolMenuPenItem;
    private JCheckBoxMenuItem toolMenuEraserItem;

    // Property
    private JMenu propertyMenu;
    private JDialog propertyMenuDialog;
    private JLabel propertyMenuDialogContrastLabel;
    private JLabel propertyMenuDialogBrightnessLabel;
    private JLabel propertyMenuDialogSaturationLabel;
    private JSlider propertyMenuDialogSaturationSlider;
    private JSpinner propertyMenuDialogPenSizeSpinner;
    private SpinnerNumberModel model = new SpinnerNumberModel(5, 1, 30, 1);
    private int penSize;
    private int eraserSize;


    private javax.swing.JSlider contrastSlide;

    private javax.swing.JMenuItem dogMask;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JMenu filter;
    private javax.swing.JMenuItem glasses1Mask;


    private javax.swing.ButtonGroup goutTypePen;
    private javax.swing.JMenuItem gray;
    private javax.swing.JMenuItem inversor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel offsetLabel;
    private javax.swing.JLabel lbSize;
    private javax.swing.JLabel lbText;
    private javax.swing.JLabel lightenLabel1;
    private javax.swing.JMenuItem morphology;
    private javax.swing.JScrollBar noiseBar;
    private javax.swing.JButton glitchWaveOKButton;
    private javax.swing.JPanel penColor;
    private javax.swing.JLabel penColorLabel;
    private javax.swing.JLabel penSizeLabel;
    private javax.swing.JPanel pnlTextColor;
    private javax.swing.JCheckBox rbtBlue;
    private javax.swing.JCheckBox rbtGreen;
    private javax.swing.JCheckBox rbtRed;
    private javax.swing.JMenuItem sepia;
    private javax.swing.JSpinner textScale;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtText;
    private javax.swing.JTextField txtWidth;
    private javax.swing.JTextField txtxLength;
    private javax.swing.JRadioButton vhs_1;
    private javax.swing.JLabel vhs_1_icon;
    private javax.swing.JLabel vhs_1_icon4;
    private javax.swing.JLabel vhs_1_icon5;
    private javax.swing.JRadioButton vhs_2;
    private javax.swing.JLabel vhs_2_icon;
    private javax.swing.JRadioButton vhs_3;
    private javax.swing.JLabel vhs_3_icon;
    private javax.swing.JRadioButton vhs_4;
    private javax.swing.JLabel vhs_4_icon;
    private javax.swing.JRadioButton vhs_date_1;
    private javax.swing.JRadioButton vhs_date_2;

    //control variables of listeners
    // selectRegion是画出一块区域进行单独编辑
//    private boolean toolMenuSelectRegionItem.isSelected() = false;


    public Window(Mat img, String title) {
        this(title);
        this.img = img;
        MatUtil.show(img, showImgRegionLabel);
        showImgRegionLabel.setSize(img.width(), img.height());
        this.setSize(img.width(), img.height());
        setResizable(true);
        setLocationRelativeTo(null);

    }

    public Window(String title) {

        initComponents();

        addMouseListeners();
        setResizable(true);
        setLocationRelativeTo(null);
        // 按下每个按键会弹出一个对应窗口
        // 设置窗口的大小
        glitchMenuWaveItemDialog.setSize(400, 200);
        glitchMenuVHSItemDialog.setSize(500, 200);
        glitchMenuLilacItemDialog.setSize(300, 100);
        propertyMenuDialog.setSize(400, 400);
        Text.setSize(400, 400);

        // 设置弹窗的位置，null指明默认是中央
        glitchMenuWaveItemDialog.setLocationRelativeTo(null);
        glitchMenuVHSItemDialog.setLocationRelativeTo(null);
        glitchMenuLilacItemDialog.setLocationRelativeTo(null);
        propertyMenuDialog.setLocationRelativeTo(null);
        Text.setLocationRelativeTo(null);


        WIDGET_SUPPORT_FILE_TYPES = new ArrayList<>();
        widgetLabelList = new ArrayList<>();
        selectedRegionLabel = new JLabel();
        // 撤销和反撤销操作用的栈
        last = new Stack<>();
        next = new Stack<>();

        WIDGET_SUPPORT_FILE_TYPES.add("JPG");
        WIDGET_SUPPORT_FILE_TYPES.add("JPEG");
        WIDGET_SUPPORT_FILE_TYPES.add("PNG");
        this.setTitle(title);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glitchMenuWaveItemDialog = new javax.swing.JDialog();
        glitchWaveOKButton = new javax.swing.JButton();
        blue = new javax.swing.JRadioButton();
        red = new javax.swing.JRadioButton();
        green = new javax.swing.JRadioButton();
        txtxLength = new javax.swing.JTextField();
        offsetLabel = new javax.swing.JLabel();
        colors = new javax.swing.ButtonGroup();
        glitchMenuVHSItemDialog = new javax.swing.JDialog();
        vhs_1 = new javax.swing.JRadioButton();
        vhs_2 = new javax.swing.JRadioButton();
        vhs_3 = new javax.swing.JRadioButton();
        vhs_4 = new javax.swing.JRadioButton();
        vhs_date_1 = new javax.swing.JRadioButton();
        vhs_date_2 = new javax.swing.JRadioButton();
        vhs_1_icon = new javax.swing.JLabel();
        vhs_2_icon = new javax.swing.JLabel();
        vhs_3_icon = new javax.swing.JLabel();
        vhs_4_icon = new javax.swing.JLabel();
        vhs_1_icon4 = new javax.swing.JLabel();
        vhs_1_icon5 = new javax.swing.JLabel();
        btnVhs = new javax.swing.JButton();
        vhs = new javax.swing.ButtonGroup();
        propertyMenuDialog = new javax.swing.JDialog();
        propertyMenuDialogContrastLabel = new javax.swing.JLabel();
        propertyMenuDialogBrightnessLabel = new javax.swing.JLabel();
        noiseBar = new javax.swing.JScrollBar();
        lightenLabel1 = new javax.swing.JLabel();
        penSizeLabel = new javax.swing.JLabel();
        propertyMenuDialogPenSizeSpinner = new javax.swing.JSpinner(model);
        penColorLabel = new javax.swing.JLabel();
        penColor = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lbSize = new javax.swing.JLabel();
        txtWidth = new javax.swing.JTextField();
        txtHeight = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btResize = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        contrastSlide = new javax.swing.JSlider();
        brightnessSlider = new javax.swing.JSlider();
        propertyMenuDialogSaturationLabel = new javax.swing.JLabel();
        propertyMenuDialogSaturationSlider = new javax.swing.JSlider();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        goutTypePen = new javax.swing.ButtonGroup();
        glitchMenuLilacItemDialog = new javax.swing.JDialog();
        rbtGreen = new javax.swing.JCheckBox();
        rbtBlue = new javax.swing.JCheckBox();
        rbtRed = new javax.swing.JCheckBox();
        Text = new javax.swing.JDialog();
        lbText = new javax.swing.JLabel();
        txtText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlTextColor = new javax.swing.JPanel();
        textScale = new javax.swing.JSpinner();
        panel = new JPanel();
        showImgRegionLabel = new JLabel();
        filler1 = new javax.swing.Box.Filler(new java.
                awt.Dimension(0, 0),
                new java.awt.Dimension(0, 0),
                new java.awt.Dimension(32767, 0));
        menuBar = new javax.swing.JMenuBar();
        openPhotoMenu = new javax.swing.JMenu();
        optionsMenu = new JMenu();
        optionsMenuUndoItem = new javax.swing.JMenuItem();
        optionsMenuRedoItem = new javax.swing.JMenuItem();
        optionsMenuWidgetItem = new javax.swing.JMenuItem();
        optionsMenuCopyItem = new javax.swing.JMenuItem();
        optionsMenuCutItem = new javax.swing.JMenuItem();
        optionsMenuSaveItem = new javax.swing.JMenuItem();
        zoomIn = new javax.swing.JMenuItem();
        zoomOut = new javax.swing.JMenuItem();
        writeText = new javax.swing.JMenuItem();
        focus = new javax.swing.JMenuItem();
        btMasks = new javax.swing.JMenu();
        dogMask = new javax.swing.JMenuItem();
        glasses1Mask = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        filter = new javax.swing.JMenu();
        gray = new javax.swing.JMenuItem();
        blur = new javax.swing.JMenuItem();
        inversor = new javax.swing.JMenuItem();
        morphology = new javax.swing.JMenuItem();
        cartoon = new javax.swing.JMenuItem();
        sepia = new javax.swing.JMenuItem();
        glitchMenu = new javax.swing.JMenu();
        glitchMenuWaveItem = new javax.swing.JMenuItem();
        glitchMenuVHSItem = new javax.swing.JMenuItem();
        glitchMenuAddItem = new javax.swing.JMenuItem();
        glitchMenuLilacItem = new javax.swing.JMenuItem();
        toolMenu = new javax.swing.JMenu();
        toolMenuSelectRegionItem = new JCheckBoxMenuItem();
        toolMenuPenItem = new JCheckBoxMenuItem();
        toolMenuEraserItem = new javax.swing.JCheckBoxMenuItem();
        propertyMenu = new javax.swing.JMenu();
        // GlitchWave是按下Glitch再按下wave后出现的弹窗
        glitchMenuWaveItemDialog.setTitle("Glitch Wave");
        glitchMenuWaveItemDialog.setResizable(false);
        // colors是GlitchWave中的三个按键
        colors.add(blue);
        blue.setText("蓝色");

        colors.add(red);
        red.setText("红色");

        colors.add(green);
        green.setText("绿色");

        glitchWaveOKButton.setText("确定");
        // 当确定按钮被按下
        glitchWaveOKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        offsetLabel.setText("偏移量:");

        GroupLayout glitchWaveLayout = new GroupLayout(glitchMenuWaveItemDialog.getContentPane());
        glitchMenuWaveItemDialog.getContentPane().setLayout(glitchWaveLayout);

        // 设置GlitchWave弹窗的水平布局
//        GroupLayout.SequentialGroup hGroup = glitchWaveLayout
//                .createSequentialGroup();
//        hGroup.addGap(17);
//        hGroup.addGroup(glitchWaveLayout.createParallelGroup(GroupLayout
//                        .Alignment.LEADING)
//                .addComponent(green));
//        hGroup.addGap(5);
//
//        hGroup.addGroup(glitchWaveLayout.createParallelGroup(GroupLayout
//                        .Alignment
//                        .LEADING)
//                .addComponent(blue)
//                .addComponent(offsetLabel));
//        hGroup.addGap(5);
//
//        hGroup.addGroup(glitchWaveLayout.createParallelGroup(GroupLayout
//                        .Alignment
//                        .LEADING)
//                .addComponent(red)
//                .addComponent(txtxLength,
//                        GroupLayout.PREFERRED_SIZE,
//                        82,
//                        GroupLayout.PREFERRED_SIZE
//                        )
//                .addComponent(okButton));
//        glitchWaveLayout.setHorizontalGroup(hGroup);
//
//        // 设置GlitchWave弹窗的垂直布局
//        GroupLayout.SequentialGroup vGroup = glitchWaveLayout
//                .createSequentialGroup();
//        vGroup.addGap(5);
//        vGroup.addGroup(glitchWaveLayout.createParallelGroup()
//                .addComponent(green)
//                .addComponent(blue)
//                .addComponent(red));
//        vGroup.addGap(5);
//        vGroup.addGroup(glitchWaveLayout.createParallelGroup()
//                .addComponent(offsetLabel)
//                .addComponent(txtxLength,
//                        GroupLayout.PREFERRED_SIZE,
//                        GroupLayout.DEFAULT_SIZE,
//                        GroupLayout.PREFERRED_SIZE)
//                .addComponent(okButton)
//        );
//        vGroup.addGap(5);
//        glitchWaveLayout.setVerticalGroup(vGroup);


        glitchWaveLayout.setHorizontalGroup(
                glitchWaveLayout.
                        createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(glitchWaveLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(glitchWaveLayout.
                                        createParallelGroup(GroupLayout.
                                                Alignment.LEADING)
                                        .addGroup(glitchWaveLayout.createSequentialGroup()
                                                .addComponent(green)
                                                .addGap(45, 45, 45)
                                                .addComponent(blue)
                                                .addGap(35, 35, 35)
                                        )
                                        .addGroup(javax.swing.GroupLayout
                                                        .Alignment.TRAILING,
                                                glitchWaveLayout.createSequentialGroup()
                                                        .addComponent(offsetLabel)
                                                        .addPreferredGap(javax.swing
                                                                .LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtxLength,
                                                                javax.swing
                                                                        .GroupLayout.PREFERRED_SIZE,
                                                                82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.
                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                        )
                                )
                                .addGroup(glitchWaveLayout.createParallelGroup(javax.swing
                                                .GroupLayout.Alignment.TRAILING)
                                        .addComponent(red)
                                        .addComponent(glitchWaveOKButton))
                                .addContainerGap(29, Short.MAX_VALUE)
                        )
        );
        // 设置垂直布局
        glitchWaveLayout.setVerticalGroup(
                glitchWaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(glitchWaveLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(glitchWaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(green)
                                        .addComponent(blue)
                                        .addComponent(red))
                                .addGap(18, 18, 18)
                                .addGroup(glitchWaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtxLength, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(glitchWaveOKButton)
                                        .addComponent(offsetLabel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        glitchMenuVHSItemDialog.setTitle("Glitch VHS");
        glitchMenuVHSItemDialog.setResizable(false);
        // vhs is ButtonGroup
        vhs.add(vhs_1);
        // vhs_1 is RadioButton
        vhs_1.setText("VHS_1");

        vhs.add(vhs_2);
        vhs_2.setText("VHS_2");

        vhs.add(vhs_3);
        vhs_3.setText("VHS_3");

        vhs.add(vhs_4);
        vhs_4.setText("VHS_4");

        vhs.add(vhs_date_1);
        vhs_date_1.setText("VHS_DATE_1");

        vhs.add(vhs_date_2);
        vhs_date_2.setText("VHS_DATE_2");

        btnVhs.setText("应用");
        btnVhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVhsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GlitchVHSLayout = new javax.swing.GroupLayout(glitchMenuVHSItemDialog.getContentPane());
        glitchMenuVHSItemDialog.getContentPane().setLayout(GlitchVHSLayout);
        // 水平布局
        GlitchVHSLayout.setHorizontalGroup(
                GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                .addComponent(vhs_1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(vhs_1_icon))
                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                .addComponent(vhs_2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(vhs_2_icon)))
                                .addGap(32, 32, 32)
                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                .addComponent(vhs_3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(vhs_3_icon)
                                                .addGap(30, 30, 30)
                                                .addComponent(vhs_date_2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(vhs_1_icon5))
                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnVhs)
                                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                                .addComponent(vhs_4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(vhs_4_icon)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(vhs_date_1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(vhs_1_icon4)))
                                .addContainerGap(65, Short.MAX_VALUE))
        );
        // 垂直布局
        GlitchVHSLayout.setVerticalGroup(
                GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(vhs_1)
                                                        .addComponent(vhs_1_icon))
                                                .addGap(18, 18, 18)
                                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(vhs_2)
                                                        .addComponent(vhs_2_icon)))
                                        .addGroup(GlitchVHSLayout.createSequentialGroup()
                                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(vhs_3)
                                                                .addComponent(vhs_3_icon))
                                                        .addComponent(vhs_date_2)
                                                        .addComponent(vhs_1_icon5))
                                                .addGap(15, 15, 15)
                                                .addGroup(GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(vhs_4)
                                                        .addComponent(vhs_4_icon)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GlitchVHSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(vhs_date_1)
                                                                .addComponent(vhs_1_icon4)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(btnVhs)
                                .addContainerGap())
        );

        propertyMenuDialog.setTitle("属性");

        propertyMenuDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                if(copy == null) return;
//                previous.push(img);
//                copy.copyTo(img);
//                MatUtil.show(img, lPhoto);
//                copy = null;
            }
        });

        propertyMenuDialogContrastLabel.setText("对比度：");

        propertyMenuDialogBrightnessLabel.setText("亮度：");

        noiseBar.setMaximum(255);
        noiseBar.setOrientation(JScrollBar.HORIZONTAL);
//        noiseBar.addMouseListener(new MouseAdapter() {
//            // 当鼠标松开的时候，即是调整结束的时候，此时应用调整内容
//            public void mouseReleased(MouseEvent evt) {
//                noiseBarMouseReleased(evt);
//            }
//        });
        noiseBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                noiseBarAdjustmentValueChanged(evt);
            }
        });

        lightenLabel1.setText("噪声:");

        penSizeLabel.setText("笔刷大小:");

        penColorLabel.setText("画笔颜色:");

        penColor.setBackground(new java.awt.Color(0, 0, 0));
        penColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penColorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout penColorLayout = new javax.swing.GroupLayout(penColor);
        penColor.setLayout(penColorLayout);
        penColorLayout.setHorizontalGroup(
                penColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 21, Short.MAX_VALUE)
        );
        penColorLayout.setVerticalGroup(
                penColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 20, Short.MAX_VALUE)
        );

        jLabel2.setText("Pincel");

        jLabel3.setText("Contraste");

        lbSize.setText("Tamannho(LxA): ");

        jLabel4.setText("Largura:");

        jLabel5.setText("Altura:");

        btResize.setText("Redimensionar");
        btResize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResizeActionPerformed(evt);
            }
        });

        jLabel6.setText("Ruído:");

        jLabel7.setText("Tamanho:");

        contrastSlide.setMinimum(-100);
        contrastSlide.setToolTipText("");
        contrastSlide.setValue(0);
        contrastSlide.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                contrastSlideStateChanged(evt);
            }
        });

        brightnessSlider.setMinimum(1);
        brightnessSlider.setToolTipText("");
        brightnessSlider.setValue(1);
        brightnessSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                brightnessSliderStateChanged(evt);
            }
        });


        propertyMenuDialogSaturationLabel.setText("饱和度：");
        propertyMenuDialogSaturationSlider.setMinimum(-100);
        propertyMenuDialogSaturationSlider.setMaximum(100);
        propertyMenuDialogSaturationSlider.setToolTipText("");
        propertyMenuDialogSaturationSlider.setValue(0);
        propertyMenuDialogSaturationSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                changeSaturation(evt);
            }
        });


        javax.swing.GroupLayout PropertysLayout = new javax.swing.GroupLayout(propertyMenuDialog.getContentPane());
        propertyMenuDialog.getContentPane().setLayout(PropertysLayout);
        PropertysLayout.setHorizontalGroup(
                PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PropertysLayout.createSequentialGroup()
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addGap(167, 167, 167)
                                                .addComponent(jLabel3))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                                                .addGap(13, 13, 13)
                                                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel4)
                                                                                                .addGap(2, 2, 2)
                                                                                                .addComponent(txtWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(jLabel5))
                                                                                        .addComponent(lbSize))
                                                                                .addGap(5, 5, 5)
                                                                                .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(penSizeLabel)
                                                                                        .addComponent(penColorLabel))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(propertyMenuDialogPenSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(penColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                                .addComponent(lightenLabel1)
                                                                .addGap(34, 34, 34)
                                                                .addComponent(noiseBar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                                .addGap(148, 148, 148)
                                                                .addComponent(jLabel2))))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(btResize))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addGap(167, 167, 167)
                                                .addComponent(jLabel6))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addGap(157, 157, 157)
                                                .addComponent(jLabel7))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(propertyMenuDialogContrastLabel)
                                                        .addComponent(propertyMenuDialogBrightnessLabel)
                                                        .addComponent(propertyMenuDialogSaturationLabel))
                                                .addGap(28, 28, 28)
                                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(propertyMenuDialogSaturationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(brightnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(contrastSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        PropertysLayout.setVerticalGroup(
                PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PropertysLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addComponent(contrastSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(brightnessSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(PropertysLayout.createSequentialGroup()
                                                .addComponent(propertyMenuDialogContrastLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(propertyMenuDialogBrightnessLabel)))
                                .addGap(18, 18, 18)
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(propertyMenuDialogSaturationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(propertyMenuDialogSaturationLabel))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(noiseBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lightenLabel1))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(4, 4, 4)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(penSizeLabel)
                                        .addComponent(propertyMenuDialogPenSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(penColorLabel)
                                        .addComponent(penColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbSize)
                                .addGap(24, 24, 24)
                                .addGroup(PropertysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addComponent(btResize)
                                .addGap(42, 42, 42))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        glitchMenuLilacItemDialog.setTitle("Glitch Lilás");

        rbtGreen.setText("Verde");

        rbtBlue.setText("Azul");

        rbtRed.setText("Vermelho");

        javax.swing.GroupLayout GlitchLilacLayout = new javax.swing.GroupLayout(glitchMenuLilacItemDialog.getContentPane());
        glitchMenuLilacItemDialog.getContentPane().setLayout(GlitchLilacLayout);
        GlitchLilacLayout.setHorizontalGroup(
                GlitchLilacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GlitchLilacLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(rbtGreen)
                                .addGap(18, 18, 18)
                                .addComponent(rbtBlue)
                                .addGap(34, 34, 34)
                                .addComponent(rbtRed)
                                .addContainerGap(38, Short.MAX_VALUE))
        );
        GlitchLilacLayout.setVerticalGroup(
                GlitchLilacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GlitchLilacLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(GlitchLilacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rbtGreen)
                                        .addComponent(rbtBlue)
                                        .addComponent(rbtRed))
                                .addContainerGap(19, Short.MAX_VALUE))
        );

        lbText.setText("Texto:");

        jLabel1.setText("Tamanho:");

        jLabel8.setText("Cor:");

        pnlTextColor.setBackground(new java.awt.Color(0, 0, 0));
        pnlTextColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTextColorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTextColorLayout = new javax.swing.GroupLayout(pnlTextColor);
        pnlTextColor.setLayout(pnlTextColorLayout);
        pnlTextColorLayout.setHorizontalGroup(
                pnlTextColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 21, Short.MAX_VALUE)
        );
        pnlTextColorLayout.setVerticalGroup(
                pnlTextColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 15, Short.MAX_VALUE)
        );

        textScale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                textScaleStateChanged(evt);
            }
        });

        javax.swing.GroupLayout TextLayout = new javax.swing.GroupLayout(Text.getContentPane());
        Text.getContentPane().setLayout(TextLayout);
        TextLayout.setHorizontalGroup(
                TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TextLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(TextLayout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(pnlTextColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lbText)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textScale, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(91, Short.MAX_VALUE))
        );
        TextLayout.setVerticalGroup(
                TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TextLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbText)
                                        .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(textScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(pnlTextColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(82, Short.MAX_VALUE))
        );
        // 当点击右上角的叉号时进行什么操作
        // pending 未保存的情况下，弹窗
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        showImgRegionLabel.setText("请选择图像");
        // pending
        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(showImgRegionLabel)
                                .addGap(0, 307, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(showImgRegionLabel)
                                .addContainerGap(306, Short.MAX_VALUE))
        );

        openPhotoMenu.setText("Open");
        openPhotoMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectImg(evt);
            }
        });
        menuBar.add(openPhotoMenu);

        optionsMenu.setText("Options");

        optionsMenuUndoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        optionsMenuUndoItem.setText("Undo");
        optionsMenuUndoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undo(evt);
            }
        });
        optionsMenu.add(optionsMenuUndoItem);

        optionsMenuRedoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                InputEvent.CTRL_MASK));
        optionsMenuRedoItem.setText("Redo");
        optionsMenuRedoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redo(evt);
            }
        });
        optionsMenu.add(optionsMenuRedoItem);

        optionsMenuWidgetItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        optionsMenuWidgetItem.setText("Widget");
        optionsMenuWidgetItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWidget(evt);
            }
        });
        optionsMenu.add(optionsMenuWidgetItem);

        optionsMenuCopyItem
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                        InputEvent.CTRL_MASK));
        // 点复制之前需要选择好区域
        optionsMenuCopyItem.setText("Copy");
        // 点击复制之后选择的区域会被复制，点击鼠标会在鼠标位置粘贴复制的内容
        optionsMenuCopyItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copySelectedRegion(evt);
            }
        });
        optionsMenu.add(optionsMenuCopyItem);

        optionsMenuCutItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        optionsMenuCutItem.setText("Cut");
        optionsMenuCutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cutActionPerformed(evt);
            }
        });
        optionsMenu.add(optionsMenuCutItem);

        optionsMenuSaveItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        optionsMenuSaveItem.setText("Save");
        optionsMenuSaveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                saveActionPerformed(evt);
                saveImg(evt);
            }
        });
        optionsMenu.add(optionsMenuSaveItem);

        zoomIn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, java.awt.event.InputEvent.SHIFT_MASK));
        zoomIn.setText("Zoom +");
        zoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInActionPerformed(evt);
            }
        });
        optionsMenu.add(zoomIn);

        zoomOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, java.awt.event.InputEvent.SHIFT_MASK));
        zoomOut.setText("Zoom -");
        zoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutActionPerformed(evt);
            }
        });
        optionsMenu.add(zoomOut);

        writeText.setText("Texto");
        writeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeTextActionPerformed(evt);
            }
        });
        optionsMenu.add(writeText);

        focus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        focus.setText("Focar");
        focus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                focusActionPerformed(evt);
            }
        });
        optionsMenu.add(focus);

        menuBar.add(optionsMenu);

        btMasks.setText("Mascaras");

        dogMask.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_MASK));
        dogMask.setText("Cachorro");
        dogMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dogMaskActionPerformed(evt);
            }
        });
        btMasks.add(dogMask);

        glasses1Mask.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        glasses1Mask.setText("Óculos 1");
        glasses1Mask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glasses1MaskActionPerformed(evt);
            }
        });
        btMasks.add(glasses1Mask);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Óculos 2");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        btMasks.add(jMenuItem1);

        menuBar.add(btMasks);

        filter.setText("Filtros");

        gray.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        gray.setText("Escala de Cinza");
        gray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grayActionPerformed(evt);
            }
        });
        filter.add(gray);

        blur.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        blur.setText("Desfocar");
        blur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blurActionPerformed(evt);
            }
        });
        filter.add(blur);

        inversor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        inversor.setText("Inverter");
        inversor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inversorActionPerformed(evt);
            }
        });
        filter.add(inversor);

        morphology.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        morphology.setText("Morfologia");
        morphology.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                morphologyActionPerformed(evt);
            }
        });
        filter.add(morphology);

        cartoon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        cartoon.setText("Desenho");
        cartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartoonActionPerformed(evt);
            }
        });
        filter.add(cartoon);

        sepia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        sepia.setText("Sépia");
        sepia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sepiaActionPerformed(evt);
            }
        });
        filter.add(sepia);

        menuBar.add(filter);

        glitchMenu.setText("Glitch");

        glitchMenuWaveItem.setAccelerator(javax.swing.KeyStroke.
                getKeyStroke(java.awt.event.KeyEvent.VK_W,
                        java.awt.event.InputEvent.SHIFT_MASK));
        glitchMenuWaveItem.setText("Wave");

        glitchMenuWaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glitchMenuWaveItemDialog.setModal(true);
                glitchMenuWaveItemDialog.setVisible(true);
            }
        });
        // 有问题的源代码
        //         点击Glitch再点击Wave一栏之后，执行actionPerformed方法，其中第二行是阻塞的方法
//        glitchWave.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                System.out.println("abcd");
//                glitchWaveActionPerformed(evt);
//            }
//        });
        glitchMenu.add(glitchMenuWaveItem);

        glitchMenuVHSItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        glitchMenuVHSItem.setText("VHS");
        glitchMenuVHSItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        glitchMenu.add(glitchMenuVHSItem);

        // bug
        glitchMenuAddItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        glitchMenuAddItem.setText("add");
        glitchMenuAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        glitchMenu.add(glitchMenuAddItem);

        glitchMenuLilacItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
                InputEvent.CTRL_MASK));
        glitchMenuLilacItem.setText("lilac");
        glitchMenuLilacItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lilacActionPerformed(evt);
            }
        });
        glitchMenu.add(glitchMenuLilacItem);

        menuBar.add(glitchMenu);

        toolMenu.setText("Tool");
        // 快捷键
        toolMenuSelectRegionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.ALT_MASK
                        | InputEvent.CTRL_MASK)
        );
        toolMenuSelectRegionItem.setText("Select Region");

        toolMenuSelectRegionItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    toolMenuPenItem.setSelected(false);
                    toolMenuEraserItem.setSelected(false);
                }
            }
        });
        // 点击选择区域之后开始按键监听
//        toolMenuSelectRegionItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                clickToolMenuSelectRegionItem(evt);
//            }
//        });
        toolMenu.add(toolMenuSelectRegionItem);

        toolMenuPenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                InputEvent.ALT_MASK
                        | InputEvent.SHIFT_MASK
                        | InputEvent.CTRL_MASK)
        );
        toolMenuPenItem.setText("Pen");
        toolMenuPenItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    toolMenuSelectRegionItem.isSelected() = false;
//                    toolMenuSelectRegionItem.setSelected(false);
                    removeRegionSelected();
                    toolMenuEraserItem.setSelected(false);
                }
            }
        });
        toolMenu.add(toolMenuPenItem);

        toolMenuEraserItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
                InputEvent.SHIFT_MASK)
        );
        toolMenuEraserItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    toolMenuSelectRegionItem.isSelected() = false;
//                    toolMenuSelectRegionItem.setSelected(false);
                    removeRegionSelected();
                    toolMenuPenItem.setSelected(false);
                }
            }
        });
        toolMenuEraserItem.setText("Eraser");
        toolMenu.add(toolMenuEraserItem);

        menuBar.add(toolMenu);

        propertyMenu.setText("Property");
        propertyMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                propertysMouseClicked(evt);
            }
        });
        menuBar.add(propertyMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
        );

        pack();
    }

    private void selectImg(MouseEvent evt) {

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this)
                == JFileChooser.APPROVE_OPTION) {

            originalImgPath = fileChooser.getSelectedFile().getAbsolutePath();

            img = MatUtil.readImg(originalImgPath);
            // pending
            nexLayerImg = MatUtil.copy(img);

            originalImg = MatUtil.copy(img);

            // pending 进行一定的缩放
            MatUtil.show(img, showImgRegionLabel);

            showImgRegionLabel.setSize(img.width(), img.height());

            this.setSize(img.width(),img.height());
            this.setLocationRelativeTo(null);
            last.clear();
            next.clear();

            showImgRegionLabel.setText("");

            updatePropertys();
        }
    }

    private void dogMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dogMaskActionPerformed

        Mat newImg = MatUtil.copy(img);

        MatUtil.dog(newImg);
        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;
        removeRegionSelected();
    }//GEN-LAST:event_dogMaskActionPerformed

    private void grayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grayActionPerformed

        Mat newImg = MatUtil.copy(img);

        if (toolMenuSelectRegionItem.isSelected()) {

            MatUtil.grayScale(newImg, MatUtil.getRect(selectedRegionLabel));
            removeRegionSelected();

        } else {
            MatUtil.grayScale(newImg);
        }

        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;
    }//GEN-LAST:event_grayActionPerformed

    private void blurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blurActionPerformed

        int blurLevel = Integer.parseInt(JOptionPane.showInputDialog(null, "Nível de desfoque", JOptionPane.WARNING_MESSAGE));

        Mat newImg = MatUtil.copy(img);

        if (toolMenuSelectRegionItem.isSelected()) {

            MatUtil.blur(newImg, blurLevel, MatUtil.getRect(selectedRegionLabel));
            removeRegionSelected();

        } else {
            MatUtil.blur(newImg, blurLevel);
        }

        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;

    }//GEN-LAST:event_blurActionPerformed

    private void inversorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inversorActionPerformed

        Mat newImg = MatUtil.copy(img);

        if (toolMenuSelectRegionItem.isSelected()) {
            MatUtil.inversor(newImg, MatUtil.getRect(selectedRegionLabel));
            removeRegionSelected();
        } else
            MatUtil.inversor(newImg);

        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;

    }//GEN-LAST:event_inversorActionPerformed

    private void glasses1MaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glasses1MaskActionPerformed

        try {

            Mat newImg = MatUtil.copy(img);

            MatUtil.glasses(newImg, MatUtil.GLASSES_1);
            MatUtil.show(newImg, showImgRegionLabel);

            last.push(img);
            img = newImg;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Combinação de efeitos inválida!");
        }
    }//GEN-LAST:event_glasses1MaskActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {

            Mat newImg = MatUtil.copy(img);

            MatUtil.glasses(newImg, MatUtil.GLASSES_2);
            MatUtil.show(newImg, showImgRegionLabel);

            last.push(img);
            img = newImg;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Combinação de efeitos inválida!");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void glitchWaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glitchWaveActionPerformed

//        br.com.ySelf.JDialog.glitchWaveDialog glitchWaveDialog1 = new glitchWaveDialog();
//        glitchWaveDialog1.main();
        glitchMenuWaveItemDialog.setModal(true);
        glitchMenuWaveItemDialog.setVisible(true);
        // 获取一个当前状态的copy，对copy进行更改，便于ctrl z 回退
        // 如果点击的是x，就不执行下面的内容了
        if (!glitchWaveOKButton.isSelected()) return;
        Mat newImg = MatUtil.copy(img);
        if (toolMenuSelectRegionItem.isSelected()) {
            MatUtil.glitchWave(newImg, waveLength, color, MatUtil.getRect(selectedRegionLabel));
            removeRegionSelected();
        } else {
            MatUtil.glitchWave(newImg, waveLength, color);
        }

        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;

    }

    private void okButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (!offsetLabel.isVisible() && !txtxLength.isVisible())
            glitchMenuWaveItemDialog.dispose();
        else {
            try {
                waveLength = Integer.parseInt(txtxLength.getText());
                /* !! ALERT: improve, make dynamic verification */
                if (green.isSelected()) {
                    color = EColor.GREEN;
                } else if (blue.isSelected()) {
                    color = EColor.BLUE;
                } else if (red.isSelected()) {
                    color = EColor.RED;
                }
                Mat newImg = MatUtil.copy(img);
                if (toolMenuSelectRegionItem.isSelected()) {
                    MatUtil.glitchWave(newImg, waveLength, color, MatUtil.getRect(selectedRegionLabel));
                    removeRegionSelected();
                } else {
                    MatUtil.glitchWave(newImg, waveLength, color);
                }

                MatUtil.show(newImg, showImgRegionLabel);

                last.push(img);
                img = newImg;
                // 执行完之后再关闭窗口
                glitchMenuWaveItemDialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "填写错误！");
            }

        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void morphologyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_morphologyActionPerformed

        try {

            int morph_size = Integer.parseInt(JOptionPane.showInputDialog(null, "Tamanho"));

            Mat newImg = MatUtil.copy(img);

            if (toolMenuSelectRegionItem.isSelected()) {
                MatUtil.morphology(newImg, morph_size, MatUtil.getRect(selectedRegionLabel));
                removeRegionSelected();
            } else {
                MatUtil.morphology(newImg, morph_size);
            }
            MatUtil.show(newImg, showImgRegionLabel);

            last.push(img);
            img = newImg;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Tente novamente!");
        }

    }//GEN-LAST:event_morphologyActionPerformed

    private void btnVhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVhsActionPerformed
        glitchMenuVHSItemDialog.dispose();
    }//GEN-LAST:event_btnVhsActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        try {

            glitchMenuVHSItemDialog.setModal(true);
            glitchMenuVHSItemDialog.setVisible(true);

            Mat newImg = MatUtil.copy(img);

            /* !! ALERT: improve, make dynamic verification */
            if (vhs_1.isSelected()) {
                MatUtil.vhs(newImg, MatUtil.VHS_1);
            } else if (vhs_2.isSelected()) {
                MatUtil.vhs(newImg, MatUtil.VHS_2);
            } else if (vhs_3.isSelected()) {
                MatUtil.vhs(newImg, MatUtil.VHS_3);
            } else if (vhs_4.isSelected()) {
                MatUtil.vhs(newImg, MatUtil.VHS_4);
            } else if (vhs_date_1.isSelected()) {
                MatUtil.vhs(newImg, MatUtil.VHS_DATE_1);
            } else {
                MatUtil.vhs(newImg, MatUtil.VHS_DATE_2);
            }

            MatUtil.show(newImg, showImgRegionLabel);

            last.push(img);
            img = newImg;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Tente novamente!");
            System.out.println("ERRO: " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            Mat newImg = MatUtil.copy(img);
            MatUtil.overlay(newImg, MatUtil.readImg(fileChooser.getSelectedFile().getAbsolutePath()));
            MatUtil.show(newImg, showImgRegionLabel);

            last.push(img);
            img = newImg;

        }

    }

    // pending
    private void formKeyPressed(KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            if (toolMenuSelectRegionItem.isSelected()) {
                removeRegionSelected();
            } else if (selectedWidgetLabel != null) {
                removeWidget();
            } else if (pasting) {
                pasting = false;
                disablePasteMode();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_F12) {
            MatUtil.show(nexLayerImg, "");
        }
    }//GEN-LAST:event_formKeyPressed

    private void propertysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propertysMouseClicked
        temp = MatUtil.copy(img);
        propertyMenuDialog.setModal(true);
        propertyMenuDialog.setVisible(true);
        last.push(img);
        img = temp;
        removeRegionSelected();
        restartPorpertyComponentsValues();
    }//GEN-LAST:event_propertysMouseClicked

    private void cartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartoonActionPerformed

        Mat newImg = MatUtil.copy(img);

        if (toolMenuSelectRegionItem.isSelected()) {
            MatUtil.cartoon(newImg, MatUtil.getRect(selectedRegionLabel));
            removeRegionSelected();
        } else
            MatUtil.cartoon(newImg);

        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;

    }//GEN-LAST:event_cartoonActionPerformed

//    private void noiseBarMouseReleased(MouseEvent evt) {
//        applyNoise(noiseBar.getValue(), true);
//    }

    private void noiseBarAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_noiseBarAdjustmentValueChanged
        applyNoise(noiseBar.getValue(), false);
    }

    private void penColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penColorMouseClicked
        // 阻塞式选择画笔颜色
        // 窗口弹出后一直到选好颜色点击确定，才会执行下一行代码
        Color color = JColorChooser
                .showDialog(null,
                        "选择颜色",
                        // 默认颜色为黑色
                        Color.BLACK);

        penColor.setBackground(color);

    }

    private void saveActionPerformed(ActionEvent evt) {

        if (img != null) {

            try {

                JFileChooser fileChooser = new JFileChooser();

                if (fileChooser.showOpenDialog(this)
                        == JFileChooser.APPROVE_OPTION) {

                    Mat newImg = MatUtil.copy(img);

                    for (JLabel widget : widgetLabelList) {
                        MatUtil.widget(newImg,
                                MatUtil.readImg(widget.getIcon().toString()),
                                widget.getX(), widget.getY());
                        panel.remove(widget);
                    }

                    MatUtil.show(newImg, showImgRegionLabel);

                    last.push(img);
                    img = newImg;

                    MatUtil.save(fileChooser.getSelectedFile()
                                    .getAbsolutePath(),
                            img);
                    JOptionPane.showMessageDialog(null,
                            "Success");

                    widgetLabelList.clear();

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao salvar, tente colocar o caminho com o nome do arquivo!");
                System.out.println(ex.getMessage());
            }

        }
    }
    // pending 2
    private void saveImg(ActionEvent e){
        if(img == null){
            JOptionPane.showMessageDialog(null,
                    "Please open an image and edit it first");
            return;
        }
        Mat newImg = MatUtil.copy(img);
        // 保存后，小组件和图片融为一体，所以把小组件删除
        for (JLabel widgetLabel : widgetLabelList) {
            MatUtil.widget(newImg,
                    MatUtil.readImg(widgetLabel.getIcon().toString()),
                    widgetLabel.getX(), widgetLabel.getY());
            panel.remove(widgetLabel);
        }
        // 显示融为一体的图片
        MatUtil.show(newImg, showImgRegionLabel);
        if(img != last.peek()){
            last.push(img);
        }
        img = newImg;

        MatUtil.save(originalImgPath,
                img);
        JOptionPane.showMessageDialog(null,
                "Success");
    }

//    private void saveAsNewImage()
    private void cutActionPerformed(ActionEvent evt) {
        // pending 弹窗的位置
        if (!toolMenuSelectRegionItem.isSelected()) {
            JOptionPane.showMessageDialog(null,
                    "Please select region first");
        } else {

            Mat newImg = MatUtil.cut(img, MatUtil.getRect(selectedRegionLabel));

            MatUtil.show(newImg, showImgRegionLabel);
            showImgRegionLabel.setSize(newImg.width(),newImg.height());
            this.setSize(newImg.width(), newImg.height());
            this.setLocationRelativeTo(null);

            last.push(img);
            img = newImg;

            removeRegionSelected();
        }
    }
    // pending1
    private void copySelectedRegion(ActionEvent evt) {

        toolMenuSelectRegionItem.setSelected(false);
        // 如果还没有选择区域，弹出对话框
        if(selectedRegionLabel.getBorder() == null) {
            // pending 对话框的位置？
            JOptionPane.showMessageDialog(null,
                    "Please select region first");
            return;
        }
        copyRegionMat = img.submat(MatUtil.getRect(selectedRegionLabel));
        // pending 下面代码的作用不明
//        JLabel lbRegion = new JLabel();
//        MatUtil.show(copyRegionMat, lbRegion);
//        selectedRegionLabel.setLayout(null);
//        selectedRegionLabel.add(lbRegion);
//        selectedRegionLabel.revalidate();
//        selectedRegionLabel.repaint();

        // 进入pasting模式
        pasting = true;
    }

    private void addWidget(ActionEvent evt) {
        // ?
        disableListeners();

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this)
                == JFileChooser.APPROVE_OPTION) {
            widgetPath = fileChooser.getSelectedFile().getAbsolutePath();
            // pending
            if (WIDGET_SUPPORT_FILE_TYPES.contains(widgetPath
                    .substring(widgetPath.lastIndexOf(".") + 1)
                    .toUpperCase())) {

                Mat widget = MatUtil.readImg(widgetPath);
                ImageIcon widgetIcon = new ImageIcon(widgetPath);
                JLabel widgetLabel = new JLabel(widgetIcon);

                addWidgetListener(widgetLabel, widgetIcon);

                widgetLabel.setBounds(this.getX() / 2, this.getY() / 2,
                        widget.width(), widget.height());

                panel.setLayout(null);
                panel.add(widgetLabel);


                // 设定各小组件优先级
                panel.setComponentZOrder(widgetLabel, 0);
                for (int i = 0; i < widgetLabelList.size(); i++) {
                    panel.setComponentZOrder(widgetLabelList.get(i), i + 1);
                }
                panel.setComponentZOrder(showImgRegionLabel,
                        widgetLabelList.size() + 1);
                panel.revalidate();
                panel.repaint();
                widgetLabelList.add(widgetLabel);


            } else {
                JOptionPane.showMessageDialog(null,
                        "only support JPG, JPEG and PNG");
            }
        }
    }

    private void redo(ActionEvent evt) {

        if (!next.isEmpty()) {

            last.push(img);

            if (!toolMenuSelectRegionItem.isSelected()) {

                img = next.pop();

            } else {

                Rect selectedRegionRect = MatUtil.getRect(selectedRegionLabel);
                Mat newImg = MatUtil.copy(img);

                next.peek().submat(selectedRegionRect)
                        .copyTo(newImg.submat(selectedRegionRect));
                img = newImg;
                removeRegionSelected();
            }
            showImgRegionLabel.setSize(img.width(),img.height());
            this.setSize(img.width(),img.height());
            this.setLocationRelativeTo(null);

            MatUtil.show(img, showImgRegionLabel);

        }
        // pending 认为不需要
//        else {
//            JOptionPane.showMessageDialog(null, "Não há mais oque refazer!");
//        }
    }

    private void undo(ActionEvent evt) {

        if (!last.isEmpty()) {
            next.push(img);
            if (!toolMenuSelectRegionItem.isSelected()) {

                img = last.pop();

            } else {

                Rect selectedRegionRect = MatUtil.getRect(selectedRegionLabel);
                Mat newImg = MatUtil.copy(img);
                // last.peek()是想要返回的最近的那一个版本
                last.peek().submat(selectedRegionRect)
                        .copyTo(newImg.submat(selectedRegionRect));
//                last.push(img);
                img = newImg;
                removeRegionSelected();
            }
            showImgRegionLabel.setSize(img.width(),img.height());
            this.setSize(img.width(), img.height());
            this.setLocationRelativeTo(null);

            MatUtil.show(img, showImgRegionLabel);

        }
        // pending  感觉不需要加弹窗
//        else {
//            JOptionPane.showMessageDialog(null, "Não há mais oque desfazer!");
//        }
    }

    private void zoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInActionPerformed

        if (toolMenuSelectRegionItem.isSelected()) {

            zoomIn.setEnabled(false);
            zoomRegion = new JPanel();
            zoomRegion.setBounds(selectedRegionLabel.getX(), selectedRegionLabel.getY(), selectedRegionLabel.getWidth(), selectedRegionLabel.getHeight());
            matZoomOut = MatUtil.copy(img);

            img = img.submat(MatUtil.getRect(zoomRegion));

            //pen image - 2nd layer
            if (nexLayerImg != null) {

                matZoomOutNexLayerImg = MatUtil.copy(nexLayerImg);
                nexLayerImg = nexLayerImg.submat(MatUtil.getRect(zoomRegion));
                MatUtil.resize(nexLayerImg, matZoomOut.size());
            }

            MatUtil.resize(img, matZoomOut.size());
            MatUtil.show(img, showImgRegionLabel);
            removeRegionSelected();

        } else
            JOptionPane.showMessageDialog(null, "Selecione a região do zoom");

    }//GEN-LAST:event_zoomInActionPerformed

    private void zoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutActionPerformed
        zoomIn.setEnabled(true);

        MatUtil.resize(img, new Size(zoomRegion.getWidth(), zoomRegion.getHeight()));

        img.copyTo(matZoomOut.submat(MatUtil.getRect(zoomRegion)));
        img = matZoomOut;

        MatUtil.show(img, showImgRegionLabel);
        nexLayerImg = matZoomOutNexLayerImg;

    }//GEN-LAST:event_zoomOutActionPerformed

    private void btResizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResizeActionPerformed

        try {

            double width = Double.parseDouble(txtWidth.getText());
            double height = Double.parseDouble(txtHeight.getText());

            Mat newImg = MatUtil.copy(temp);
            MatUtil.resize(newImg, new Size(width, height));

            last.push(img);
            img = temp = newImg;

            MatUtil.show(temp, showImgRegionLabel);

            updatePropertys();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Pre-encha corretamente os dados!");
        }

    }//GEN-LAST:event_btResizeActionPerformed

    private void sepiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sepiaActionPerformed

        Mat newImg = MatUtil.copy(img);

        if (toolMenuSelectRegionItem.isSelected()) {

            MatUtil.sepia(newImg, MatUtil.getRect(selectedRegionLabel));
            removeRegionSelected();

        } else
            MatUtil.sepia(newImg);


        last.push(img);
        img = newImg;
        MatUtil.show(img, showImgRegionLabel);

    }//GEN-LAST:event_sepiaActionPerformed

    private void lilacActionPerformed(java.awt.event.ActionEvent evt) {
        //GEN-FIRST:event_lilacActionPerformed

        glitchMenuLilacItemDialog.setModal(true);
        glitchMenuLilacItemDialog.setVisible(true);

        if (rbtBlue.isSelected() || rbtGreen.isSelected() || rbtRed.isSelected()) {
            Mat newImg = MatUtil.copy(img);
            last.push(newImg);
            img = temp;
        }
    }//GEN-LAST:event_lilacActionPerformed

    private void contrastSlideStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_contrastSlideStateChanged
        contrastAndBrightness();
    }//GEN-LAST:event_contrastSlideStateChanged

    private void brightnessSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_brightnessSliderStateChanged
        contrastAndBrightness();
    }//GEN-LAST:event_brightnessSliderStateChanged


    private void pnlTextColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTextColorMouseClicked

        Color color = JColorChooser.showDialog(null,
                "选择颜色",
                Color.BLACK);
        text.setColor(new Scalar(color.getBlue(), color.getGreen(), color.getRed()));

        writeText();

    }//GEN-LAST:event_pnlTextColorMouseClicked

    private void writeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeTextActionPerformed

        if (toolMenuSelectRegionItem.isSelected()) {

            text = new Text();

            Text.setModal(true);
            Text.setVisible(true);

            last.push(img);
            img = temp;

            removeRegionSelected();
        } else
            JOptionPane.showMessageDialog(null, "Selecione a área do texto!");

    }//GEN-LAST:event_writeTextActionPerformed

    private void textScaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_textScaleStateChanged

        text.setScale((int) textScale.getValue());
        writeText();
    }//GEN-LAST:event_textScaleStateChanged

    private void focusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_focusActionPerformed

        if (toolMenuSelectRegionItem.isSelected()) {

            Mat newImg = MatUtil.copy(img);
            MatUtil.focus(newImg, MatUtil.getRect(selectedRegionLabel));
            MatUtil.show(newImg, showImgRegionLabel);

            last.push(img);
            img = newImg;

            removeRegionSelected();

        } else
            JOptionPane.showMessageDialog(null, "Selecione a área que deseja focar!");
    }

    private void changeSaturation(ChangeEvent evt) {

        temp = MatUtil.copy(img);

        int saturation = propertyMenuDialogSaturationSlider.getValue();
        lastSaturation = propertyMenuDialogSaturationSlider.getValue();

        if (toolMenuSelectRegionItem.isSelected()) {
            MatUtil.saturation(temp, saturation, MatUtil.getRect(selectedRegionLabel));
        } else {
            MatUtil.saturation(temp, saturation);
        }

        MatUtil.show(temp, showImgRegionLabel);

    }

    private void addMouseListeners() {

        panel.addMouseListener(new MouseAdapter() {

            // pending
            @Override
            public void mouseClicked(MouseEvent e) {
                // pending 下面的代码是让组件边界高亮消失的，但是我把组件边界高亮取消了
                // 所以现在没必要
//                removeWidgetSelection();
                if (pasting) {
                    paste();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (toolMenuSelectRegionItem.isSelected()) {
                    addRegion(e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // pending
                /*
                画笔的时候，鼠标按下->拖拽->松开是一个画画行为的完成，当松开的时候我们将上一个状态入栈，然后更改img
                 */
                if (toolMenuPenItem.isSelected()
                        || toolMenuEraserItem.isSelected()) {
                    last.add(img);
                    if (paintingImg != null) {
                        img = MatUtil.copy(paintingImg);
                    }
                    // 下面这行代码是必须的，这样可以保证每次绘画的时候是在现在图像的基础上进画
                    // 如果没有这行代码，paintImg保持的只是上一个画好的状态，如果之后做了其他操作，将不会显示
                    paintingImg = null;
                }
            }

        });

        panel.addMouseMotionListener(new MouseAdapter() {
            // 拖拽的时候将会一直调用该方法
            @Override
            public void mouseDragged(MouseEvent e) {
                if (toolMenuSelectRegionItem.isSelected()) {
                    setRegionSize(e.getX(), e.getY());
                    // pending
                } else if (toolMenuPenItem.isSelected()) {
                    penSize = (Integer) propertyMenuDialogPenSizeSpinner.getValue();
                    paint(e.getX(), e.getY());
                } else if (toolMenuEraserItem.isSelected()) {
                    // pending
                    eraserSize = (Integer) propertyMenuDialogPenSizeSpinner.getValue();
                    erase(e.getX(), e.getY());
                }
            }

            // pending
            @Override
            public void mouseMoved(MouseEvent e) {
                // 粘贴模式下，粘贴框随鼠标一起移动
                if (pasting) {
                    // REGION就是将要复制的区域大小，是一个轮廓
                    selectedRegionLabel.setLocation(e.getPoint());
                    selectedRegionLabel.repaint();
                }
            }
        });

        ActionListener cbx = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean[] colors = {
                        rbtBlue.isSelected(),
                        rbtGreen.isSelected(),
                        rbtRed.isSelected()
                };

                temp = MatUtil.copy(img);

                MatUtil.lilac(temp, colors);
                MatUtil.show(temp, showImgRegionLabel);
            }
        };

        txtText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                text.setStr(txtText.getText());
                writeText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                text.setStr(txtText.getText());
                writeText();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                text.setStr(txtText.getText());
                writeText();

            }
        });


        rbtBlue.addActionListener(cbx);
        rbtGreen.addActionListener(cbx);
        rbtRed.addActionListener(cbx);
    }


    private void writeText() {
        temp = MatUtil.copy(img);
        MatUtil.writeText(text, temp, MatUtil.getRect(selectedRegionLabel));
        MatUtil.show(temp, showImgRegionLabel);
    }

    private void paint(int x, int y) {
        // 调整界面大小，让图片填充后可以删掉
        // pending
        if (x > img.width() || y > img.height()) return;
        // pending
        if (paintingImg == null) {
            paintingImg = MatUtil.copy(img);
        }
        MatUtil.paint(new int[]{
                        penColor.getBackground().getBlue(),
                        penColor.getBackground().getGreen(),
                        penColor.getBackground().getRed()},
                penSize,
                penSize,
                x,
                y,
                paintingImg);

        // 实现笔画的方法是，鼠标的坐标+penSize构成Rect，将img的rect区域进行像素更改

        MatUtil.show(paintingImg, showImgRegionLabel);

    }

    // pending 加一个erase的大小调节功能
    // 一键清除功能
    private void erase(int x, int y) {
        // pending
        if (x > img.width() || y > img.height()) return;

        if (paintingImg == null) {
            paintingImg = MatUtil.copy(img);
        }

        Mat eraseRegion = paintingImg.submat(new Rect(x, y, eraserSize,
                eraserSize));

        // pending
            /*if (zoomOut.isEnabled())
                img2 = nexLayerImg.submat(MatUtil.getRect(zoomRegion))
                        .submat(new Rect(x, y, width, height));
            else

             */
        Mat originalRegion = originalImg.submat(new Rect(x, y, eraserSize, eraserSize));
        // 拿原图覆盖现在正在画的图，就相当于橡皮擦操作
        MatUtil.overlay(eraseRegion, originalRegion);

        MatUtil.show(paintingImg, showImgRegionLabel);

    }

    private void paste() {

        Mat newImg = MatUtil.copy(img);
        // newImg 的 selectRegion 被 copyRegionMat的内容覆盖
        MatUtil.copyToRegion(newImg,
                copyRegionMat,
                MatUtil.getRect(selectedRegionLabel));

        MatUtil.show(newImg, showImgRegionLabel);

        last.push(img);
        img = newImg;
    }

    private void addRegion(Point p) {

        selectedRegionLabel.setLocation(p);
        selectedRegionLabel.setSize(1, 1);
        selectedRegionLabel.setBorder(BorderFactory
                .createLineBorder(Color.cyan));

        panel.setLayout(null);
        panel.add(selectedRegionLabel);

        // 在panel的z轴视角上设置各组件的优先级/遮盖关系：index小的，优先级高
        panel.setComponentZOrder(selectedRegionLabel, 0);
        for (int i = 0; i < widgetLabelList.size(); i++) {
            panel.setComponentZOrder(widgetLabelList.get(i), i + 1);
        }
        panel.setComponentZOrder(showImgRegionLabel,
                widgetLabelList.size() + 1);

        panel.revalidate();
        panel.repaint();

        selectedRegionX = selectedRegionLabel.getX();
        selectedRegionY = selectedRegionLabel.getY();
    }

    private void setRegionSize(int x, int y) {

        int width = Math.abs(selectedRegionX - x);
        int height = Math.abs(selectedRegionY - y);
        x = Math.min(x, selectedRegionX);
        y = Math.min(y, selectedRegionY);
        selectedRegionLabel.setBounds(x, y, width, height);

    }


    // pending
    private void removeRegionSelected() {
        panel.setLayout(null);
        panel.remove(selectedRegionLabel);
        panel.revalidate();
        panel.repaint();
        toolMenuSelectRegionItem.setSelected(false);
    }

    private void removeWidget() {
        widgetLabelList.remove(selectedWidgetLabel);
        panel.setLayout(null);
        panel.remove(selectedWidgetLabel);
        panel.repaint();
        panel.revalidate();
    }

    private void contrastAndBrightness() {

        temp = MatUtil.copy(img);

        if (toolMenuSelectRegionItem.isSelected())
            MatUtil.contrastAndBrightness(temp, brightnessSlider.getValue(), -contrastSlide.getValue(), MatUtil.getRect(selectedRegionLabel));
        else
            MatUtil.contrastAndBrightness(temp, brightnessSlider.getValue(), -contrastSlide.getValue());


        MatUtil.show(temp, showImgRegionLabel);
    }

    private void applyNoise(int level, boolean replace) {
        copy = MatUtil.copy(img);
        if (toolMenuSelectRegionItem.isSelected()) {
            MatUtil.noise(copy, level, MatUtil.getRect(selectedRegionLabel));
        } else {
            MatUtil.noise(copy, level);
        }
        // 需要再加一个确定键，确定之后img入栈，然后替换为当前调整后的内容
        MatUtil.show(copy, showImgRegionLabel);

        if (replace) {
            last.push(img);
            img = copy;
        }

    }

    private void restartPorpertyComponentsValues() {
        Component[] components = propertyMenuDialog.getContentPane().getComponents();
        for (Component c : components) {
            if (c instanceof JScrollBar) {
                ((JScrollBar) c).setValue(0);
            }
        }
    }

    private void disablePasteMode() {
        removeRegionSelected();
        selectedRegionLabel.removeAll();
        copyRegionMat = null;
    }

    private void disableListeners() {
        toolMenuSelectRegionItem.setSelected(false);
        pasting = false;
    }

    public Mat getImg() {
        return img;
    }

    public void setNextLayerMat(Mat img) {
        this.nexLayerImg = img;
    }

    private void updatePropertys() {

        txtWidth.setText(img.width() + "");
        txtHeight.setText(img.height() + "");
        lbSize.setText("Tamanho (LxA): " + img.width() + "x" + img.height());

        int width = img.width() >= 200 ? img.width() : 200;
        int height = img.height() >= 200 ? img.height() : 200;


        this.setSize(width, height);
        this.setLocationRelativeTo(null);


    }


    private void addWidgetListener(JLabel widgetLabel, ImageIcon widgetIcon) {
        flag = false;
        Window win = this;
        int width = widgetIcon.getIconWidth();
        int height = widgetIcon.getIconHeight();
        MouseInputAdapter mia = new MouseInputAdapter() {
            int ex;
            int ey;

            public void mousePressed(MouseEvent e) {
                win.selectedWidgetLabel = widgetLabel;
                ex = e.getX();
                ey = e.getY();

//                widgetLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            }

            public void mouseReleased(MouseEvent e) {
                flag = false;
//                widgetLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE.brighter()));
            }

            public void mouseDragged(MouseEvent e) {

                int lx = widgetLabel.getX();
                int ly = widgetLabel.getY();
                int x = e.getX() + lx;
                int y = e.getY() + ly;
                if (isInResizeArea(e, widgetLabel)) {
//                    widgetLabel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                    flag = true;
                    int dx = x - lx;
                    double percent = (double) dx / (double) width;
                    // 同比例缩放
                    dx = (int) (percent * width);
                    int dy = (int) (percent * height);
                    Image originalImage = widgetIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(
                            dx,
                            dy,
                            Image.SCALE_SMOOTH
                    );
                    widgetLabel.setIcon(new ImageIcon(scaledImage));
                    widgetLabel.setBounds(lx, ly, dx, dy);
                } else if (!flag) {
                    widgetLabel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    widgetLabel.setLocation(x - ex, y - ey);
                }
                panel.revalidate();
                panel.repaint();
            }

            public void mouseEntered(MouseEvent e) {
                if (isInResizeArea(e, widgetLabel)) {
                    widgetLabel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                }
            }

            public void mouseExited(MouseEvent e) {
                if (!isInResizeArea(e, widgetLabel)) {
                    widgetLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }

            public void mouseMoved(MouseEvent e) {
                if (isInResizeArea(e, widgetLabel)) {
                    widgetLabel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));

                } else {
                    widgetLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                }
            }
        };

        widgetLabel.addMouseListener(mia);
        widgetLabel.addMouseMotionListener(mia);

    }

    public static boolean isInResizeArea(MouseEvent e, JLabel widgetLabel) {
        int rx = widgetLabel.getWidth();
        int ry = widgetLabel.getHeight();
        int x = e.getX();
        int y = e.getY();
        if (rx - 20 < x && x < rx + 20 && ry - 20 < y && y < ry + 20) {
            return true;
        }
        return false;
    }


    public void removeWidgetSelection() {

        if (selectedWidgetLabel != null) {
            selectedWidgetLabel.setBorder(null);
            selectedWidgetLabel = null;
        }
    }


    // End of variables declaration//GEN-END:variables

}
