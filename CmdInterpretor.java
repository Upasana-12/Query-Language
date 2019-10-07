import java.io.FileWriter;
import java.util.*;
import java.io.*;

class CREATE
{
    String stmt,table_name;
    String[] attr;
    CREATE(String s)
    {
        stmt=s;
    }  
    
    void check_create()
    {
        String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
        String[] keywords=new String[]{"table", "create", "as", "values"};
        List<String> lc=Arrays.asList(commands);
        List<String> lk=Arrays.asList(keywords);

        int p=stmt.length()-2;
        if(stmt.charAt(p)!=')')
        {
            System.out.println("Missing right parentheses");

        }
        else
        {
        String[] words=stmt.split(" ");
        if(words.length<=2)
        {
            System.out.println("Invalid create");
        }
        else
        {
        if(words[1].compareToIgnoreCase("table")==0)
        {
            if(!(lc.contains(words[2]) || lk.contains(words[2])))
            {
                if(words.length<4)
                {
                String s=new String(words[2]);
                int i=0,si=0,ei;
                ei=s.indexOf("(");
                table_name=s.substring(0, ei); // table name
                System.out.println(table_name);
                String a=s.substring(ei+1,s.length()-2);
                if(a=="" || a==" ")
                System.out.println("Incomplete create command");
                else
                {

                 attr=a.split(","); // attributes
                 int j,flag=0;
                 for(i=0;i<attr.length;i++)
                 {
                      flag=0;
                     for(j=i+1;j<attr.length;i++)
                     {
                         if(attr[i].compareToIgnoreCase(attr[j])==0)
                         {
                             flag++;
                             break;
                         }
                     }
                 }
                 if(flag==0)
                 {
                     try
                     {
                         FileWriter fw=new FileWriter(table_name+".txt");
                         for(i=0;i<attr.length-1;i++)
                         {
                             fw.write(attr[i]+",");
                         } 
                         fw.write(attr[attr.length-1]);
                         fw.close();
                         System.out.println("file created");
                     }
                     catch(Exception e)
                     {
                         System.out.println("error in creating file");
                     }
                     System.out.println("printing attrs");
                for(i=0;i<attr.length;i++)
                {
                    System.out.println(attr[i]);
                }
                }
            }
                }
                else
                {
                    if(words[3].compareToIgnoreCase("as")==0)
                    {
                        if(words[4].compareToIgnoreCase("select")==0)
                        {

                        }
                        else
                        {
                            System.out.println(("invalid create"));
                        }
                    }
                    else
                    {
                        System.out.println(("invalid create")); 
                    }
                }
            }
            else
            {
                System.out.println("Invalid create");    
            }
        }
        else
        {
            System.out.println("Invalid create");
        }
        }
        }
    }
}

class INSERT
{
    String stmt,table_name;
    String[] attr;
    INSERT(String s)
    {
        stmt=s;
    }  

    void check_insert()
    {
        String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
        String[] keywords=new String[]{"table", "create", "as", "values", "into"};
        List<String> lc=Arrays.asList(commands);
        List<String> lk=Arrays.asList(keywords);


        int p=stmt.length()-2;
        if(stmt.charAt(p)!=')')
        {
            System.out.println("Missing right parentheses");
            
        }
        else
        {
        String[] words=stmt.split(" ");
        if(words.length<=2)
        {
            System.out.println("Invalid insert");
        }
        else
        {
        if(words[1].compareToIgnoreCase("into")==0)
        {
            if(!(lc.contains(words[2]) || lk.contains(words[2])))
            {
                table_name=words[2];
                System.out.println(table_name);
                
                String s=new String(words[3]);
                int i=0,si=0,ei;
                ei=s.indexOf("(");
                String k=s.substring(0, ei); 
                if(k.compareToIgnoreCase("values")==0)
                {
                //System.out.println(table_name);
                String a=s.substring(ei+1,s.length()-2);
                if(a=="" || a==" ")
                System.out.println("Incomplete create command");
                else
                {
                 attr=a.split(","); // attributes
                 FileWriter fw = null;
                 BufferedWriter bw = null;
                 PrintWriter pw = null;

                 try {
                   fw = new FileWriter(table_name+".txt", true);
                   bw = new BufferedWriter(fw);
                     pw = new PrintWriter(bw);
                    pw.println();
                    for(i=0;i<attr.length-1;i++)
                    {
                        pw.print(attr[i]+",");
                    }
                    pw.print(attr[attr.length-1]);
                    System.out.println("Data Successfully appended into file");
                    pw.flush();
        
                } catch(Exception e)
                {
                    System.out.println("file not found");
                }
                finally {
                    try {
                        pw.close();
                        bw.close();
                        fw.close();
                    } catch (IOException io) {// can't do anything }
                    }
                }
                System.out.println("printing attributes values:");
                for(i=0;i<attr.length;i++)
                {
                    System.out.println(attr[i]);
                }
                
                }
            }
                else
                {
                    System.out.println("Invalid insert");     
                }
            }
            else
            {
                System.out.println("Invalid insert");    
            }
        }
        else
        {
            System.out.println("Invalid insert");
        }
        }
        }
    } 
}

class PRINT
{
    String stmt,table_name;
    PRINT(String s)
    {
        stmt=s;
    }
    void check_print()
    {
        String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
        String[] keywords=new String[]{"table", "create", "as", "values", "into"};
        List<String> lc=Arrays.asList(commands);
        List<String> lk=Arrays.asList(keywords);

        String[] words=stmt.split(" ");
        table_name=words[1].substring(0,words[1].length()-1);
        if(!(lc.contains(table_name) || lk.contains(table_name)))
        {
            try 
            {
             FileReader fr=new FileReader(table_name+".txt");
             int i=0;
             System.out.println("-------------------------------");
             System.out.println("Table name: "+table_name);
             while((i=fr.read())!=-1)
             {
                 System.out.print((char)i);
             }   
             System.out.println();
             System.out.println("-------------------------------");
             fr.close();
            } catch (Exception e) 
            {
                System.out.println("Table not found");
            }
           System.out.println();
             System.out.println("printing table name: "+table_name);
        }
        else
        {
            System.out.println("invalid print");
        }
    }
}

class STORE
{
    String stmt,table_name;
    STORE(String s)
    {
        stmt=s;
    }
    void check_store()
    {
        String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
        String[] keywords=new String[]{"table", "create", "as", "values", "into"};
        List<String> lc=Arrays.asList(commands);
        List<String> lk=Arrays.asList(keywords);

        String[] words=stmt.split(" ");
        table_name=words[1].substring(0,words[1].length()-1);
        if(!(lc.contains(table_name) || lk.contains(table_name)))
        {
           // table_name=words[1].substring(0,words[1].length()-1);
            System.out.println("storing "+table_name);
        }
        else
        {
            System.out.println("invalid store");
        }
    }
}

class LOAD
{
    String stmt,table_name;
    LOAD(String s)
    {
        stmt=s;
    }
    void check_load()
    {
        String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
        String[] keywords=new String[]{"table", "create", "as", "values", "into"};
        List<String> lc=Arrays.asList(commands);
        List<String> lk=Arrays.asList(keywords);

        String[] words=stmt.split(" ");
        table_name=words[1].substring(0,words[1].length()-1);
        if(!(lc.contains(table_name) || lk.contains(table_name)))
        {
           // table_name=words[1].substring(0,words[1].length()-1);
           try 
           {
            FileReader fr=new FileReader(table_name+".txt");
            int i=0;
            System.out.println("-------------------------------");
            System.out.println("Table name: "+table_name);
            while((i=fr.read())!=-1)
            {
                System.out.print((char)i);
            }   
            System.out.println();
            System.out.println("-------------------------------");
            fr.close();
           } catch (Exception e) 
           {
               System.out.println("Table not found");
           }
          System.out.println();
            System.out.println("printing table name: "+table_name);
        }
        else
        {
            System.out.println("invalid load");
        }
    }
}

class SELECT
{
    String stmt,table_name;
    String[] attr;
    String[] input_attr;
   //ArrayList<String> attr=new ArrayList<String>();
   SELECT(String s)
   {
       stmt=s;
   }
   void check_select()
   {
    String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
    String[] keywords=new String[]{"table", "create", "as", "values", "into","*", "from"};
    List<String> lc=Arrays.asList(commands);
    List<String> lk=Arrays.asList(keywords);

        stmt=stmt.substring(0,stmt.length()-1);
        String[] words=stmt.split(" ");
        if(words.length<4)
        {
            System.out.println("Invalid select");
        }
        else
        {
            int i,flag=0;
            String line;
            try 
            {
             BufferedReader br=new BufferedReader(new FileReader(words[3]+".txt"));
             line=br.readLine();
             System.out.println(line);
             br.close();
        
            line = line.replaceAll("\\s", "");
            attr=line.split(",");
            List<String> a=Arrays.asList(attr);
            input_attr=words[1].split(",");
            for(i=0;i<input_attr.length;i++)
            {
                flag=0;
                if(!(a.contains(input_attr[i])))
                {
                    flag++;
                    break;
                }
            }
            if(flag==0)
            {
                // read from file and print
                System.out.println("heyy");
            }
        } catch (Exception e) 
        {
            System.out.println("Table not found");
        }
        }
    
    }
}

public class CmdInterpretor
{
    public static void main(String[] args)
    {
        String[] commands=new String[]{"create", "print", "insert", "load", "store", "exit", "select", "quit"};
        String[] keywords=new String[]{"table", "create", "as", "values", "into"};
        Scanner sc=new Scanner(System.in);
        String statement=new String();
        statement=sc.nextLine();
        int l=statement.length()-1;
        String q=statement.substring(0, l);
        System.out.println(q);
        if(statement.charAt(l)!=';')
        {
            System.out.println("Missing ; in the end");
        }
        else if(q.compareToIgnoreCase("exit")==0 || q.compareToIgnoreCase("quit")==0)
        {
                System.exit(0);
        }
        else
        {
        List<String> lc=Arrays.asList(commands);
        List<String> lk=Arrays.asList(keywords);
         statement=statement.trim().replaceAll("( )+"," ");
       // System.out.println(statement);
        String[] words=statement.split(" ");
        if(lc.contains(words[0]))
        {
            System.out.println("valid 1st word");
            if(words[0].compareToIgnoreCase("create")==0)
            {
                CREATE ob = new CREATE(statement);
                ob.check_create();
               
            }
            else if(words[0].compareToIgnoreCase("print")==0)
            {
                PRINT ob=new PRINT(statement);
                ob.check_print();
            }
            else if(words[0].compareToIgnoreCase("select")==0)
            {
                SELECT ob=new SELECT(statement);
                ob.check_select();
            }
            else if(words[0].compareToIgnoreCase("insert")==0)
            {
                INSERT ob=new INSERT(statement);
                ob.check_insert();
            }
            else if(words[0].compareToIgnoreCase("store")==0)
            {
                STORE ob = new STORE(statement);
                ob.check_store();   
            }
            else if(words[0].compareToIgnoreCase("load")==0)
            {
                LOAD ob = new LOAD(statement);
                ob.check_load();
            }
            
            else
            {
                System.out.println("invalid cmd");
            }
            
        }
        else
        {
            System.out.println("invalid 1st word");
        }
    }
    }
}