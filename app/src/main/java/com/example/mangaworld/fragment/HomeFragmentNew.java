package com.example.mangaworld.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaworld.R;
import com.example.mangaworld.activity.AllMangaActivity;
import com.example.mangaworld.activity.LoadActivity;
import com.example.mangaworld.activity.MainActivity;
import com.example.mangaworld.adapter.ItemClickInterface;
import com.example.mangaworld.adapter.MangaRecyclerviewAdapter;
import com.example.mangaworld.adapter.TopMangaRecyclerViewAdapter;
import com.example.mangaworld.adapter.ViewPagerSlideAdapter;
import com.example.mangaworld.model.Manga;
import com.example.mangaworld.transformer.ZoomOutPageTransformer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragmentNew extends Fragment {
    public HomeFragmentNew() {
        // Required empty public constructor
    }
    MainActivity mainAcdsctivity;

    //slider
    private Handler mhandler = new Handler();
    private ViewPagerSlideAdapter viewPagerSlideAdapter;
    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator3;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ArrayList<Manga> slideArrayList ;

    //popular
    private RecyclerView recycleViewPopulation;
    private ShimmerFrameLayout shimmerPopular;
    private TextView tvSeeAllPopular;
    private ArrayList<Manga> popularMangaArrayList;
    private MangaRecyclerviewAdapter mangaRecyclerviewAdapter;

    //manhua
    private RecyclerView recycleViewManhua;
    private ShimmerFrameLayout shimmerManhua;
    private TextView tvSeeAllManhua;
    private ArrayList<Manga> manhuaMangaArrayList;
    private MangaRecyclerviewAdapter manhuaRecyclerviewAdapter;

    //manhwa
    private RecyclerView recycleViewManhwa;
    private ShimmerFrameLayout shimmerManhwa;
    private TextView tvSeeAllManhwa;
    private ArrayList<Manga> manhwaMangaArrayList;
    private MangaRecyclerviewAdapter manhwaRecyclerviewAdapter;

    //top manga
    private RecyclerView recycleViewTopManga;
    private ShimmerFrameLayout shimmerTopManga;
    private TextView tvSeeAllTop;
    private ArrayList<Manga> topMangaArrayList;
    private TopMangaRecyclerViewAdapter topMangaRecyclerviewAdapter;

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //n???u slide di chuy???n ?????n cu???i th?? set l???i v??? ?????u
            if(viewPager2.getCurrentItem() == slideArrayList.size() - 1){
                viewPager2.setCurrentItem(0);
            }
            else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    public HomeFragmentNew(MainActivity mainActivity) {
        this.mainAcdsctivity = mainActivity;
    }

    public static HomeFragmentNew newInstance(String str, String str2, MainActivity mainActivity) {
        HomeFragmentNew mainFragment = new HomeFragmentNew(mainActivity);
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    public static HomeFragmentNew newInstance(String param1, String param2) {
        HomeFragmentNew fragment = new HomeFragmentNew();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_new, container, false);
        LoadSlide(view);
        LoadPopular(view);
        LoadManhua(view);
        LoadManhwa(view);
        LoadTopManga(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //gi??? slide ???? hi???n tr?????c ???? khi quay l???i m??n h??nh n??y
        mhandler.postDelayed(runnable, 3000);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        shimmerPopular.stopShimmer();
        shimmerPopular.setVisibility(View.GONE);
        shimmerManhua.stopShimmer();
        shimmerManhua.setVisibility(View.GONE);
        shimmerManhwa.stopShimmer();
        shimmerManhwa.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        mhandler.removeCallbacks(runnable);
    }

    public void LoadSlide(View view){
        viewPager2 = view.findViewById(R.id.viewPagerTitle);
        indicator3 = view.findViewById(R.id.indicator3);
        shimmerFrameLayout = view.findViewById(R.id.contentShimmerRecommended);
        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        slideArrayList = new ArrayList<>();

        Manga slide = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i n??o");
        Manga slide1 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650651552/lbm9xyslmguocztmrdvu.jpg","Ch??? ?????p");
        Manga slide2 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650650848/rgvre1n9fmnhrpnlrd82.jpg","Quang n??");
        Manga slide3 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i ph???n 2");
        slideArrayList.add(slide);
        slideArrayList.add(slide1);
        slideArrayList.add(slide2);
        slideArrayList.add(slide3);

        viewPagerSlideAdapter = new ViewPagerSlideAdapter(slideArrayList);
        //click v??o t???ng slider
        viewPagerSlideAdapter.setOnClickItemRecyclerView(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),slideArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        viewPager2.setAdapter(viewPagerSlideAdapter);
        indicator3.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mhandler.removeCallbacks(runnable);
                mhandler.postDelayed(runnable, 3000);
            }
        });
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    public void LoadPopular(View view){
        recycleViewPopulation = view.findViewById(R.id.recycleViewPopulation);
        tvSeeAllPopular = view.findViewById(R.id.btnPopular);
        shimmerPopular = view.findViewById(R.id.contentShimmerPopular);
        shimmerPopular.startShimmer();
        shimmerPopular.setVisibility(View.VISIBLE);
        popularMangaArrayList = new ArrayList<>();

        Manga slide = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i n??o", 30000, "H??nh ?????ng");
        Manga slide1 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650651552/lbm9xyslmguocztmrdvu.jpg","Ch??? ?????p", 50000, "H??i h?????c");
        Manga slide2 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650650848/rgvre1n9fmnhrpnlrd82.jpg","Quang n??", 80000, "Vi???n t?????ng, Si??u nhi??n");
        Manga slide3 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i ph???n 2", 10000, "B?? ?????o");
        popularMangaArrayList.add(slide);
        popularMangaArrayList.add(slide1);
        popularMangaArrayList.add(slide2);
        popularMangaArrayList.add(slide3);

        mangaRecyclerviewAdapter = new MangaRecyclerviewAdapter(popularMangaArrayList);
        //click v??o t???ng n??t +
        mangaRecyclerviewAdapter.setOnClickItemRecyclerView(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),String.valueOf(popularMangaArrayList.get(position).getPrice()), Toast.LENGTH_SHORT).show();
            }
        });
        recycleViewPopulation.setAdapter(mangaRecyclerviewAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewPopulation.setLayoutManager(layoutManager);

        tvSeeAllPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truy???n qua cho n?? 1 ch??? s??? ????? nh???n bi???t d??? li???u khi d??ng chung m??n h??nh
                //vd s??? 1 l?? see all popular, s??? 2 l?? see all manhhua, s??? 3 l?? see all manhwa
                Intent intent = new Intent(getContext(), AllMangaActivity.class);
                startActivity(intent);
            }
        });

        shimmerPopular.stopShimmer();
        shimmerPopular.setVisibility(View.GONE);
    }

    public void LoadManhua(View view){
        recycleViewManhua = view.findViewById(R.id.recycleViewManhua);
        tvSeeAllManhua = view.findViewById(R.id.btnManhua);
        shimmerManhua = view.findViewById(R.id.contentShimmerManhua);
        shimmerManhua.startShimmer();
        shimmerManhua.setVisibility(View.VISIBLE);
        manhuaMangaArrayList = new ArrayList<>();

        Manga slide = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i n??o", 30000, "H??nh ?????ng");
        Manga slide1 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650651552/lbm9xyslmguocztmrdvu.jpg","Ch??? ?????p", 50000, "H??i h?????c");
        Manga slide2 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650650848/rgvre1n9fmnhrpnlrd82.jpg","Quang n??", 80000, "Vi???n t?????ng, Si??u nhi??n");
        Manga slide3 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i ph???n 2", 10000, "B?? ?????o");
        manhuaMangaArrayList.add(slide);
        manhuaMangaArrayList.add(slide1);
        manhuaMangaArrayList.add(slide2);
        manhuaMangaArrayList.add(slide3);

        manhuaRecyclerviewAdapter = new MangaRecyclerviewAdapter(manhuaMangaArrayList);
        //click v??o t???ng n??t +
        manhuaRecyclerviewAdapter.setOnClickItemRecyclerView(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),String.valueOf(manhuaMangaArrayList.get(position).getPrice()), Toast.LENGTH_SHORT).show();
            }
        });
        recycleViewManhua.setAdapter(manhuaRecyclerviewAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewManhua.setLayoutManager(layoutManager);

        tvSeeAllManhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truy???n qua cho n?? 1 ch??? s??? ????? nh???n bi???t d??? li???u khi d??ng chung m??n h??nh
                //vd s??? 1 l?? see all popular, s??? 2 l?? see all manhhua, s??? 3 l?? see all manhwa
                Intent intent = new Intent(getContext(), AllMangaActivity.class);
                startActivity(intent);
            }
        });

        shimmerManhua.stopShimmer();
        shimmerManhua.setVisibility(View.GONE);
    }

    public void LoadManhwa(View view){
        recycleViewManhwa = view.findViewById(R.id.recycleViewManhwa);
        tvSeeAllManhwa = view.findViewById(R.id.btnManhwa);
        shimmerManhwa = view.findViewById(R.id.contentShimmerManhwa);
        shimmerManhwa.startShimmer();
        shimmerManhwa.setVisibility(View.VISIBLE);
        manhwaMangaArrayList = new ArrayList<>();

        Manga slide = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i n??o", 30000, "H??nh ?????ng");
        Manga slide1 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650651552/lbm9xyslmguocztmrdvu.jpg","Ch??? ?????p", 50000, "H??i h?????c");
        Manga slide2 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650650848/rgvre1n9fmnhrpnlrd82.jpg","Quang n??", 80000, "Vi???n t?????ng, Si??u nhi??n");
        Manga slide3 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i ph???n 2", 10000, "B?? ?????o");
        manhwaMangaArrayList.add(slide);
        manhwaMangaArrayList.add(slide1);
        manhwaMangaArrayList.add(slide2);
        manhwaMangaArrayList.add(slide3);

        manhwaRecyclerviewAdapter = new MangaRecyclerviewAdapter(manhwaMangaArrayList);
        //click v??o t???ng n??t +
        manhwaRecyclerviewAdapter.setOnClickItemRecyclerView(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),String.valueOf(manhwaMangaArrayList.get(position).getPrice()), Toast.LENGTH_SHORT).show();
            }
        });
        recycleViewManhwa.setAdapter(manhwaRecyclerviewAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewManhwa.setLayoutManager(layoutManager);

        tvSeeAllManhwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truy???n qua cho n?? 1 ch??? s??? ????? nh???n bi???t d??? li???u khi d??ng chung m??n h??nh
                //vd s??? 1 l?? see all popular, s??? 2 l?? see all manhhua, s??? 3 l?? see all manhwa
                Intent intent = new Intent(getContext(), AllMangaActivity.class);
                startActivity(intent);
            }
        });

        shimmerManhwa.stopShimmer();
        shimmerManhwa.setVisibility(View.GONE);
    }

    private void LoadTopManga(View view) {
        recycleViewTopManga = view.findViewById(R.id.recycleViewGenres);
        tvSeeAllTop = view.findViewById(R.id.btnTop);
        shimmerTopManga = view.findViewById(R.id.contentShimmerGener);
        shimmerTopManga.startShimmer();
        shimmerTopManga.setVisibility(View.VISIBLE);
        topMangaArrayList = new ArrayList<>();

        Manga slide = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i n??o", 30000, "H??nh ?????ng");
        Manga slide1 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650651552/lbm9xyslmguocztmrdvu.jpg","Ch??? ?????p", 50000, "H??i h?????c");
        Manga slide2 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1650650848/rgvre1n9fmnhrpnlrd82.jpg","Quang n??", 80000, "Vi???n t?????ng, Si??u nhi??n");
        Manga slide3 = new Manga("https://res.cloudinary.com/dmfrvd4tl/image/upload/v1638689117/cqihlyiwovjgksu3jnmy.jpg","Ti???c t??ng th??i ph???n 2", 10000, "B?? ?????o");
        topMangaArrayList.add(slide);
        topMangaArrayList.add(slide1);
        topMangaArrayList.add(slide2);
        topMangaArrayList.add(slide3);

        topMangaRecyclerviewAdapter = new TopMangaRecyclerViewAdapter(topMangaArrayList);
        //click v??o t???ng n??t +
        topMangaRecyclerviewAdapter.setOnClickItemRecyclerView(new ItemClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),String.valueOf(manhwaMangaArrayList.get(position).getPrice()), Toast.LENGTH_SHORT).show();
            }
        });
        recycleViewTopManga.setAdapter(topMangaRecyclerviewAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewTopManga.setLayoutManager(layoutManager);

        tvSeeAllTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truy???n qua cho n?? 1 ch??? s??? ????? nh???n bi???t d??? li???u khi d??ng chung m??n h??nh
                //vd s??? 1 l?? see all popular, s??? 2 l?? see all manhhua, s??? 3 l?? see all manhwa
                Intent intent = new Intent(getContext(), AllMangaActivity.class);
                startActivity(intent);
            }
        });

        //t???t shimmer
        shimmerTopManga.stopShimmer();
        shimmerTopManga.setVisibility(View.GONE);
    }
}