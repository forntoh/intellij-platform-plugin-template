package com.forntoh.mvvmtemplates.recipes.app

fun baseActivity(packageName: String) = """package $packageName

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    protected lateinit var navController: NavController

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    
    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()
    
}"""

fun baseViewModel(packageName: String) = """package $packageName

abstract class BaseViewModel : ViewModel()"""

fun scopedFragment(packageName: String) = """package $packageName

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import $packageName.utils.getLoadingDialog
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class ScopedFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var imageRequest: ImageRequest.Builder

    @Inject
    lateinit var eventBus: EventBus

    protected val navController: NavController by lazy { NavHostFragment.findNavController(this) }

    val dialog by lazy { requireContext().getLoadingDialog() }

    val root: View by lazy { requireActivity().findViewById(android.R.id.content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
//        eventBus.events.onEach { event ->
//            when (event) {
//                else -> throw IllegalStateException("Event type not handled: +event")
//            }
//        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }

    inline fun loadWithLoadingDialog(task: () -> Unit) {
        dialog.show(); task(); dialog.dismiss()
    }
}""".replace('+','$')

fun mainActivity(packageName: String) = """package $packageName

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import $packageName.R
import $packageName.databinding.ActivityMainBinding
import $packageName.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    internal lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            viewmodel = viewModel
        }
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()

        setupActionBarWithNavController(this, navController, AppBarConfiguration.Builder().build())

        viewModel.preferenceRepository.useSystemUiMode = true

        viewModel.preferenceRepository.nightModeLive.observe(this) { nightMode ->
            nightMode?.let { delegate.localNightMode = it }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    
}"""

fun mainViewModel(packageName: String) = """package $packageName

import $packageName.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {}"""