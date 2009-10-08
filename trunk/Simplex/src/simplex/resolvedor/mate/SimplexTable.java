package simplex.resolvedor.mate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Marco
 */
public class SimplexTable {

private String colNames[];
private String rowData[][];
private String varBasics[];
private String varNs[];
private String varTOT[];
private DefaultTableModel modelo;

    public DefaultTableModel SimplexTable( Ecuacion FOc, Ecuacion restC[] ){
        System.out.println(varBasics.length);
        setVarTOT();
        setColNames ( varNs, varBasics );
        modelo = newTable( colNames );
        setRowData( FOc, restC);
        return modelo;
    }
    
    public DefaultTableModel SimplexTable( String var[], String varBasic[] ){
        setVarNs( var );
        setVarBasics( varBasic );
        setVarTOT();
        setColNames ( varNs, varBasics );
        //setRowData();
        modelo = newTable( colNames );
        return modelo;
    }

    private DefaultTableModel newTable( String Colums[] ){
        JTable jTable1 = new JTable();
        DefaultTableModel modNuevo = new DefaultTableModel();
        for (int i=0; i<colNames.length; i++){
            modNuevo.addColumn(colNames[i]);
        }
        return modNuevo;
    }

    private void setColNames( String var[], String varBasic[] ){
        int vars = var.length + varBasic.length;
        int numCol = vars + 4;
        colNames = new String[numCol];
        colNames[0] = "Variables Basicas";
        colNames[1] = "Numero Ecuacion";
        colNames[colNames.length-2] = "b";
        colNames[colNames.length-1] = "div";
        for (int i=2, j=0; i<colNames.length-2; i++, j++){
            colNames[i] = varTOT[j];
        }
    }

    private void setRowData(Ecuacion FO, Ecuacion[] restric ){
        String RowFO[];
        RowFO = new String[colNames.length];
        RowFO[0] = "Z";
        RowFO[1] = "0";
        RowFO[colNames.length-2] = Integer.toString(FO.getResultado());
        Monomio temp[] = FO.getMonomios();
        for (int i=2; i<colNames.length-2; i++){
            for (int j=0; j<temp.length; j++){
                String caract = Character.toString(temp[j].getVariable());
                if ( caract.equalsIgnoreCase(colNames[i])  ){
                    RowFO[i] = Integer.toString( temp[j].getCoeficiente());
                }
            }
        }
        //Llenado de valores Null con 0
        for (int i=0; i<RowFO.length; i++){
            if ( RowFO[i] == null ) RowFO[i] = "0";
        }
        RowFO[colNames.length-1] = null;
        modelo.addRow(RowFO);
    }

    public void setVarBasics( String varBasic[] ){
        varBasics = varBasic;
    }
    public String[] getVarBasics(){
        return varBasics;
    }

    public void setVarNs( String vars[] ){
        varNs = vars;
    }

    public String[] getVarNs(){
        return varNs;
    }

    private void setVarTOT(){
        varTOT = new String[(varBasics.length + varNs.length)];
        for (int i=0; i<varNs.length; i++){
            varTOT[i] = varNs[i];
        }
        for (int i=0, j=varNs.length; j<varTOT.length; i++, j++ ){
            varTOT[j] = varBasics[i];
        }
    }
    public String[] getVarTOT(){
        return varTOT;
    }
    
}