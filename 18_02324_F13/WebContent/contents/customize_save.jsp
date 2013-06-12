<%
    int i;
    String combinedData = "";
    String[] layout = new String[5];
    String[] selectedCharts = new String[10];
    
    /* Reads the selected layout into array */
    for (i = 0; i < 5; i++) {
        layout[i] = request.getParameter("row" + i);
    }
    
    /* Reads the selected charts into array */
    for (i = 0; i < 10; i++) {
        selectedCharts[i] = request.getParameter("chart" + i);
    }
    
    
    /* Combines all the data to one big string, so it is easy to save */
    for(i=0; i<5; i++) {
        combinedData = combinedData + layout[i] + ",";
    }
    
    combinedData = combinedData + ";";
    
    for(i=0; i<10; i++) {
        combinedData = combinedData + selectedCharts[i] + ",";
    }
    
    
    /* Saves data to txt-file */
    String path = application.getRealPath("/") + "customized_charts.txt";
    java.io.FileWriter filewriter = new java.io.FileWriter(path);
    filewriter.write(combinedData);
    filewriter.close();
%>

Your data is saved. See the result <a href="./?page=statistics">here.</a>