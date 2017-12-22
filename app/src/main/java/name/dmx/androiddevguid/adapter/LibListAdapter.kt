package name.dmx.androiddevguid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import name.dmx.androiddevguid.R
import name.dmx.androiddevguid.model.LibInfo
import java.util.*

/**
 * Created by dmx on 2017/12/21.
 */
class LibListAdapter(private val context: Context, var data: List<LibInfo>) : RecyclerView.Adapter<LibListAdapter.MyViewHolder>() {
    var onItemClickListener: OnItemClickListener? = null
    private val random = Random()
    private val colorArray: IntArray

    init {
        colorArray = context.resources.getIntArray(R.array.randomColor)
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val item = data[position]
        val name = item.packageName.substring(item.packageName.lastIndexOf('.') + 1)
        holder?.launcher?.setBackgroundColor(colorArray[position%8])
        holder?.launcher?.text = name
        holder?.name?.text = item.packageName
        holder?.downloadCount?.text = getDownloadCountStr(item._count)
//        val strArr = item.detail.split(" ")
//        val detail = if (strArr.size == 5) strArr[1] + " " + strArr[2] else strArr[1]
//        holder?.updateTime?.text = detail
        holder?.view?.tag = position
    }

    private fun getDownloadCountStr(downloadCount: Int): String {
        return downloadCount.toString() + "次引用"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lib, parent, false)
        val viewHolder = MyViewHolder(view)
        view.setOnClickListener {
            onItemClickListener?.onItemClick(view, view.tag as Int)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder : RecyclerView.ViewHolder {
        var view: View
        var launcher: TextView
        var name: TextView
        var downloadCount: TextView
        var updateTime: TextView

        constructor(view: View) : super(view) {
            this.view = view
            this.launcher = view.findViewById(R.id.sdvLauncher)
            this.name = view.findViewById(R.id.tvName)
            this.downloadCount = view.findViewById(R.id.tvDownloadCount)
            this.updateTime = view.findViewById(R.id.tvUpdateTime)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}