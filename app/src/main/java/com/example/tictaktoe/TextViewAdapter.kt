import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


public class TextViewAdapter(var activity: Activity, var context: Context, var stringBoard: Array<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var textView: TextView
        if (convertView == null) {
            textView = TextView(this.context)
        } else {
            textView = TextView(context)
        }
        textView.text = stringBoard[position]
        return textView
    }

    override fun getItem(position: Int): Any {
        return stringBoard[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return stringBoard.size
    }

}