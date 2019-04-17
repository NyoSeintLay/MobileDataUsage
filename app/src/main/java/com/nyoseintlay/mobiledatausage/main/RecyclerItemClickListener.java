package com.nyoseintlay.mobiledatausage.main;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */


import com.nyoseintlay.mobiledatausage.model.DataUsageRaw;

import java.util.ArrayList;

public interface RecyclerItemClickListener {
    void onItemClick(ArrayList<DataUsageRaw> dataUsageRawArrayList);
}
