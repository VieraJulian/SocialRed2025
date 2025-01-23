INSERT INTO permissions (permission_name) VALUES 
    ('EDIT_PROFILE'), 
    ('CREATE_POST'), 
    ('COMMENT_ON_POSTS'), 
    ('LIKE_POSTS'), 
    ('FOLLOW_USERS'), 
    ('SEND_ACCEPT_FRIEND_REQUESTS'), 
    ('EDIT_DELETE_USER_PROFILES'), 
    ('BLOCK_SUSPEND_ACCOUNTS'), 
    ('DELETE_ANY_POSTS_COMMENTS')
ON CONFLICT (permission_name) DO NOTHING;


INSERT INTO roles (role_name) VALUES 
    ('USER'), 
    ('ADMIN')
ON CONFLICT (role_name) DO NOTHING;

INSERT INTO roles_permissions (permission_id, role_id) VALUES 
    (1,1), 
    (2,1), 
    (3,1), 
    (4,1), 
    (5,1), 
    (6,1), 
    (7,2), 
    (8,2), 
    (9,2)
ON CONFLICT (permission_id, role_id) DO NOTHING;