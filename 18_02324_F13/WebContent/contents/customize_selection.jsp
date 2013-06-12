Select the charts you would like on your customized statistics page...
<br />
<form action="./?page=customize_layout" method="post">
    <div class="selection_box_container">
        Top 10 Bar-charts:
        <div class="selection_box">
            <input type="checkbox" name="chart0" />For the last 10 minutes
            <br />
            <input type="checkbox" name="chart1" />For the last hour
            <br />
            <input type="checkbox" name="chart2" />For the last day
            <br />
            <input type="checkbox" name="chart3" />For the last month
        </div>
    </div>

    <div class="selection_box_container">
        Top 5 Line-charts:
        <div class="selection_box">
            <input type="checkbox" name="chart4" />For the last 10 minutes
            <br />
            <input type="checkbox" name="chart5" />For the last hour
            <br />
            <input type="checkbox" name="chart6" />For the last day
            <br />
            <input type="checkbox" name="chart7" />For the last month
        </div>
    </div>

    <div class="selection_box_container">
        Top 10 Pie-charts:
        <div class="selection_box">
            <input type="checkbox" name="chart8" />For the last 10 minutes
            <br />
            <input type="checkbox" name="chart9" />For the last hour
            <br />
            <input type="checkbox" name="chart10" />For the last day
            <br />
            <input type="checkbox" name="chart11" />For the last month
        </div>
    </div>
    
    <div class="clear"></div>
    
    <br /><br /><br /><br />
    Select the division of the space on your customized statistics page...
    <br /><br />
    You can select up to 10 small charts, up to 5 big charts, or any combination of the two.
    Select for the 5 availible rows, if you want 1 big chart, 2 small charts or no charts.
    <br /><br />
    
    <table id="customize_layout">
        <tr>
            <td id="customize_row">
                Row #
            </td>
            <td class="customize_option">
                One big
                <br />
                <img src="./images/chart_single.png" />
            </td>
            <td class="customize_option">
                Two small
                <br />
                <img class="example" src="./images/chart_double.png" />
            </td>
            <td class="customize_option">
                None
            </td>
        </tr>
        <tr>
            <td>1</td>
            <td><input type="radio" name="row0" value="1" checked /></td>
            <td><input type="radio" name="row0" value="2" /></td>
            <td><input type="radio" name="row0" value="0" disabled="disabled" /></td>
        </tr>
        <tr>
            <td>2</td>
            <td><input type="radio" name="row1" value="1" /></td>
            <td><input type="radio" name="row1" value="2" /></td>
            <td><input type="radio" name="row1" value="0" checked /></td>
        </tr>
        <tr>
            <td>3</td>
            <td><input type="radio" name="row2" value="1" /></td>
            <td><input type="radio" name="row2" value="2" /></td>
            <td><input type="radio" name="row2" value="0" checked /></td>
        </tr>
        <tr>
            <td>4</td>
            <td><input type="radio" name="row3" value="1" /></td>
            <td><input type="radio" name="row3" value="2" /></td>
            <td><input type="radio" name="row3" value="0" checked /></td>
        </tr>
        <tr>
            <td>5</td>
            <td><input type="radio" name="row4" value="1" /></td>
            <td><input type="radio" name="row4" value="2" /></td>
            <td><input type="radio" name="row4" value="0" checked /></td>
        </tr>
    </table>
    
    <input class="form_submit" type="submit" value="Save changes" />
    
    <div class="clear"></div>
</form>
