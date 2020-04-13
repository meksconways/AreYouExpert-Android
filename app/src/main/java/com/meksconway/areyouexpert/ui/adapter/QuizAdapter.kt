package com.meksconway.areyouexpert.ui.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.Answer
import kotlinx.android.synthetic.main.item_question.view.*

class QuizAdapter constructor(private val callback: (Answer?) -> Unit) :
    RecyclerView.Adapter<QuizAdapter.QuizAnwersVH>() {

    private val answersData = arrayListOf<Answer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAnwersVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuizAnwersVH(view)
    }

    override fun getItemCount() = answersData.size

    override fun onBindViewHolder(holder: QuizAnwersVH, position: Int) {
        val model = answersData[position]
        holder.bind(model)
        holder.itemView.cardQuestion.setOnClickListener {
            if (!model.isSelected) {
                answersData.filter {
                    it.answerText != model.answerText
                }.map { it.isSelected = false }
                callback.invoke(model)
            } else {
                callback.invoke(null)
            }
            model.isSelected = !model.isSelected
            notifyDataSetChanged()

        }
    }

    fun setItems(result: List<Answer>) {
        answersData.clear()
        answersData.addAll(result)
        notifyDataSetChanged()
    }

    fun disableAllItems() {
        answersData.map {
            it.disabled = true
        }
        notifyDataSetChanged()
    }

    class QuizAnwersVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cardQuestion = itemView.findViewById<MaterialCardView>(R.id.cardQuestion)
        private val questionText = itemView.findViewById<TextView>(R.id.txtQuestion)

        fun bind(model: Answer) {

            if (model.disabled){
                itemView.isEnabled = false
            }

            val question = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(model.answerText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                Html.fromHtml(model.answerText)
            }
            questionText?.text = question
            if (model.isSelected) {
                cardQuestion.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        model.resources.primaryColor
                    )
                )

            } else {
                cardQuestion.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.transparent
                    )
                )

            }

        }

    }
}