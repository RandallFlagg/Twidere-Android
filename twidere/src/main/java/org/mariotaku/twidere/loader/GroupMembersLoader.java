/*
 * 				Twidere - Twitter client for Android
 * 
 *  Copyright (C) 2012-2014 Mariotaku Lee <mariotaku.lee@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.loader;

import android.content.Context;
import android.support.annotation.NonNull;

import org.mariotaku.microblog.library.MicroBlog;
import org.mariotaku.microblog.library.MicroBlogException;
import org.mariotaku.microblog.library.twitter.model.Paging;
import org.mariotaku.microblog.library.twitter.model.ResponseList;
import org.mariotaku.microblog.library.twitter.model.User;
import org.mariotaku.twidere.model.ParcelableCredentials;
import org.mariotaku.twidere.model.ParcelableUser;
import org.mariotaku.twidere.model.UserKey;

import java.util.List;

public class GroupMembersLoader extends CursorSupportUsersLoader {

    private final String mGroupId;
    private final String mGroupName;

    public GroupMembersLoader(final Context context, final UserKey accountKey, final String groupId,
                              final String groupName, final List<ParcelableUser> data, boolean fromUser) {
        super(context, accountKey, data, fromUser);
        mGroupId = groupId;
        mGroupName = groupName;
    }

    @NonNull
    @Override
    public ResponseList<User> getCursoredUsers(@NonNull final MicroBlog twitter, @NonNull ParcelableCredentials credentials, @NonNull final Paging paging)
            throws MicroBlogException {
        if (mGroupId != null)
            return twitter.getGroupMembers(mGroupId, paging);
        else if (mGroupName != null)
            return twitter.getGroupMembersByName(mGroupName, paging);
        throw new MicroBlogException("list_id or list_name and user_id (or screen_name) required");
    }

}
