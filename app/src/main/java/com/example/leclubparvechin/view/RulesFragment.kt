package com.example.leclubparvechin.view

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.leclubparvechin.R
import java.io.File

class RulesFragment : Fragment() {

    private lateinit var pdfRenderer: PdfRenderer
    private lateinit var currentPage: PdfRenderer.Page
    private lateinit var imageView: ImageView
    private var currentPageIndex = 0

    private val pdfFileName = "essentiel_regle_golf.pdf" // Nom du fichier PDF dans le dossier assets

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rules, container, false)
        imageView = view.findViewById(R.id.imageView)

        view.findViewById<Button>(R.id.nextPageButton).setOnClickListener {
            showNextPage()
        }

        loadPdf()
        return view
    }

    private fun loadPdf() {
        // Accède au fichier PDF depuis le dossier assets
        val file = File(requireContext().cacheDir, pdfFileName)
        if (!file.exists()) {
            requireContext().assets.open(pdfFileName).use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }

        // Ouvrir le fichier PDF avec PdfRenderer
        val fileDescriptor: ParcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(fileDescriptor)

        showPage(currentPageIndex)
    }

    private fun showPage(index: Int) {
        if (index < 0 || index >= pdfRenderer.pageCount) return

        // Fermer la page précédente si elle existe
        if (::currentPage.isInitialized) {
            currentPage.close()
        }

        // Ouvrir la nouvelle page
        currentPage = pdfRenderer.openPage(index)
        val bitmap = Bitmap.createBitmap(currentPage.width, currentPage.height, Bitmap.Config.ARGB_8888)
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        imageView.setImageBitmap(bitmap)

        currentPageIndex = index
    }

    private fun showNextPage() {
        if (currentPageIndex + 1 < pdfRenderer.pageCount) {
            showPage(currentPageIndex + 1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::currentPage.isInitialized) {
            currentPage.close()
        }
        pdfRenderer.close()
    }
}
