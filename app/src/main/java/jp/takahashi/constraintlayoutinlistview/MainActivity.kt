package jp.takahashi.constraintlayoutinlistview

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView

/**
 * MainActivity
 */
class MainActivity : Activity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onStart() {
		super.onStart()

		val listView = findViewById<ListView>(R.id.listView)
		val adapter = Adapter(this)
		listView.adapter = adapter

		adapter.addAll((1..400).filter { true })
	}
}

class Adapter(context: Context) : ArrayAdapter<Int>(context, 0) {
	override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
		val row = (convertView as RowView?) ?: RowView(context)
		row.bind(getItem(position))
		return row
	}
}

class RowView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	init {
		inflate(context, R.layout.layout_row_view, this)
	}

	fun bind(value: Int) {
		Log.v("", "called! ${value}")

		val view = findViewById<TextView>(R.id.text1)
		view.text = "%03d".format(value)

		val lower = findViewById<View>(R.id.lower_group)
		lower.visibility = if (value % 3 == 0) {
			View.GONE
		} else {
			View.VISIBLE
		}
		setBackgroundColor(when (value % 3) {
			0 -> Color.argb(32, 255, 0, 0)
			1 -> Color.argb(32, 0, 255, 0)
			2 -> Color.argb(32, 0, 0, 255)
			else -> Color.argb(255, 0, 0, 0)
		})
	}
}