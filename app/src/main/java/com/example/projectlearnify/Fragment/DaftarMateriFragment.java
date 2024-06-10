//package com.example.projectlearnify.Fragment;
//
//import android.os.Bundle;
//import android.widget.FrameLayout;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.projectlearnify.MateriFragment1;
//import com.example.projectlearnify.MateriFragment2;
//import com.example.projectlearnify.MateriFragment3;
//import com.example.projectlearnify.R;
//import com.google.android.material.tabs.TabLayout;
//
//public class MainActivity extends AppCompatActivity {
//
//    FrameLayout frameLayout;
//    TabLayout tabLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.daftarmaterifragment);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        frameLayout = findViewById(R.id.framelayout);
//        tabLayout = findViewById(R.id.tablayout);
//
//        int commit;
//        commit = getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new materifragment1())
//                .addToBackStack(null)
//                .commit();
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                MateriFragment2 fragment = null;
//                switch (tab.getPosition()) {
//                    case 0:
//                        fragment = new materifragment1();
//                        break;
//
//                    case 1:
//                        fragment = new materifragment2();
//                        break;
//
//                    case 2:
//                        fragment = new materifragment3();
//                        break;
//                }
//
//                assert fragment != null;
//                int commit1 = getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//    }
//
//    private class materifragment1 extends MateriFragment2 {
//    }
//
//    private class materifragment2 extends MateriFragment2 {
//    }
//
//    private class materifragment3 extends MateriFragment2 {
//    }
//}
